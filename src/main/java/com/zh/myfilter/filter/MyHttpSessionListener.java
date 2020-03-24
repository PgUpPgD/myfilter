package com.zh.myfilter.filter;

import com.zh.myfilter.utils.PeopleCountUtil;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener()
public class MyHttpSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("创建session");
        PeopleCountUtil.countAdd();
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("销毁session");
        PeopleCountUtil.countSubtract();
    }

}
