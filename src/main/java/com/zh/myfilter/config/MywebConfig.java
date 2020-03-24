package com.zh.myfilter.config;

import com.zh.myfilter.filter.CrossFilter;
import com.zh.myfilter.filter.MyFilter;
import com.zh.myfilter.filter.MyHttpSessionListener;
import com.zh.myfilter.filter.MyInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MywebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("view跳转执行了");
        registry.addViewController("/user/index").setViewName("index");
        registry.addViewController("/user/login").setViewName("login");
        registry.addViewController("/user/stulist").setViewName("stulist");
        registry.addViewController("/user/noPerm").setViewName("noPerm");
        registry.addViewController("/user/transfer").setViewName("transfer");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/user/**");
        System.out.println("interceptors");
    }
    @Bean
    @Order(1) //过滤器可根据order值，从小到大进行执行
    public FilterRegistrationBean<CrossFilter> crossRegist() {
        FilterRegistrationBean<CrossFilter> frBean = new FilterRegistrationBean<>();
        frBean.setFilter(new CrossFilter());
        frBean.addUrlPatterns("/*");
        System.out.println("filter");
        return frBean;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" }) //禁止显示警告
//    @Bean
//    @Order(2)
    public FilterRegistrationBean<MyFilter> filterRegist() {
        FilterRegistrationBean<MyFilter> frBean = new FilterRegistrationBean<>();
        frBean.setFilter(new MyFilter());
        frBean.addUrlPatterns("/*");
        System.out.println("filter");
        return frBean;
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletListenerRegistrationBean<MyHttpSessionListener> listenerRegist() {
        ServletListenerRegistrationBean<MyHttpSessionListener> srb = new ServletListenerRegistrationBean<>();
        srb.setListener(new MyHttpSessionListener());
        System.out.println("listener");
        return srb;
    }
}
