package com.zh.myfilter.config;

import com.zh.myfilter.filter.MyLogoutFilter;
import com.zh.myfilter.realm.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //shiro资源过滤配置
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //未登陆情况下，访问需要登陆后才能访问资源时，跳转到指定资源（比如登陆页面）
        shiroFilterFactoryBean.setLoginUrl("login");
        // 当没有权限访问某些资源时，跳转到的资源
        shiroFilterFactoryBean.setUnauthorizedUrl("noPerm");

        // 存放自定义的filter
        LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
        //配置自定义登出 覆盖 logout 之前默认的LogoutFilter
        filtersMap.put("logout", myLogoutFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // authc:必须认证通过才可以访问;
        // anon: 允许匿名访问
        //不使用注解时，根据shiro的相关的内置过滤器名，来进行数据的过滤
        //使用注解时，需要配置三个bean（下方）
        //filterChainDefinitionMap.put("/user/list","perms[user:list]");

        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/index", "anon");
        filterChainDefinitionMap.put("/user/stulist", "anon");
        filterChainDefinitionMap.put("/user/noPerm", "anon");
        filterChainDefinitionMap.put("/layui/**", "anon");
        filterChainDefinitionMap.put("/user/login.do", "anon");
        filterChainDefinitionMap.put("/user/signIn.do", "anon");
//        //访问资源需要的权限,注意顺序问题(使用配置)
        filterChainDefinitionMap.put("/user/findAll.do", "authc");
        filterChainDefinitionMap.put("/user/peopleCount.do", "authc");
//
        //退出时指定logout过滤器
        filterChainDefinitionMap.put("/user/logout","logout");
        //配置记住我或认证通过可以访问的地址
        filterChainDefinitionMap.put("/user", "user");
        //必须放在所有权限设置的最后，匹配的是不满足前面匹配条件的资源
        filterChainDefinitionMap.put("/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    //安全管理对象
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        //管理realm
        defaultSecurityManager.setRealm(customRealm());
        //管理rememberMe
        defaultSecurityManager.setRememberMeManager(rememberMeManager());
        return defaultSecurityManager;
    }

    // 自定义realm对象
    @Bean
    public MyRealm customRealm() {
        MyRealm customRealm = new MyRealm();
        // 设置密码匹配器
        customRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return customRealm;
    }

    //cookie对象
    @Bean
    public SimpleCookie rememberMeCookie(){
        System.out.println("rememberMe对象");
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName("rememberMe");
        //记住我cookie生效时间 ,单位秒
        simpleCookie.setMaxAge(30);
        return simpleCookie;
    }

    //cookie管理对象
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        System.out.println("rememberMe管理器对象");
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
//        rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
//        cookieRememberMeManager.setCipherKey(Base64.decode("2AvVhdsgUs0FSA3SDFAdag=="));
        cookieRememberMeManager.setCipherKey("ZHANGXIAOHEI_CAT".getBytes());
        return cookieRememberMeManager;
    }

    //该三个Bean，表示支持在controller中可用注解在方法上进行权限的认证
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    //hash匹配器
    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {  //创建了哈希凭证的匹配器
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        // 散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        // 散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(1);
        // storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        return hashedCredentialsMatcher;
    }

    private MyLogoutFilter myLogoutFilter(){
        MyLogoutFilter logoutFilter = new MyLogoutFilter();
        //设置重定向的地址
        logoutFilter.setRedirectUrl("login");
        return logoutFilter;
    }

}
