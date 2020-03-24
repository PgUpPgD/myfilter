package com.zh.myfilter.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class MyLogoutFilter extends LogoutFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        // 获取主体对象
        Subject subject = getSubject(request,response);
        //登出
        subject.logout();
        //获取登出后重定向到的地址
        String redirectUrl = getRedirectUrl(request,response,subject);
        //重定向
        System.out.println("登出的重定向执行了");
        issueRedirect(request,response,redirectUrl);
        return false;
    }

}
