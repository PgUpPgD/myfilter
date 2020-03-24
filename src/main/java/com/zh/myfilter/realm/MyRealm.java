package com.zh.myfilter.realm;

import com.zh.myfilter.common.CodeMsg;
import com.zh.myfilter.entity.UserEntity;
import com.zh.myfilter.exception.MyException;
import com.zh.myfilter.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.List;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    @Lazy  //使用redis缓存shiro中的数据时，需要使用该注解
    private UserService userService;

    @Autowired
    private CodeMsg codeMsg;

    //获取授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权执行了");
        //获取合法登录的用户名
        String name = (String)principalCollection.getPrimaryPrincipal();
        //查询拥有的权限
        List<String> permsList = userService.findPermsByName(name);
        //创建授权信息对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(new HashSet<>(permsList));
        return info;
    }

    //获取认证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证执行了");
        //获取用户身份信息
        String name = (String)authenticationToken.getPrincipal();
        UserEntity user = userService.findByUserName(name);
        SimpleAuthenticationInfo info = null;
        if (user == null){
            throw new MyException(1,codeMsg.getUserIsEmpty());
        } else {
            //身份认证的信息对象
            //第一个参数，用户身份信息，用户名  第二个，用户凭证信息，密码   第三个，realm的名称
            info = new SimpleAuthenticationInfo(name, user.getPass(), this.getName());
//            如果md5中使用盐值，需要在认证信息对象设置盐值
            info = new SimpleAuthenticationInfo(name, user.getPass(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        }
        return info;
    }

    public static void main(String[] args) {
        SimpleHash simpleHash = new SimpleHash("md5", "123", "y9ryNkMoItnZLqn2zsjd", 1);
        System.out.println(simpleHash.toHex());
    }
}
