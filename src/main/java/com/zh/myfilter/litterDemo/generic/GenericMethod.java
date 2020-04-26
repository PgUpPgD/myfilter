package com.zh.myfilter.litterDemo.generic;

import com.zh.myfilter.litterDemo.entity.Order;
import com.zh.myfilter.litterDemo.entity.User;

public class GenericMethod {
    //泛型方法
    public <M> M method(M m){
        System.out.println(m);
        return m;
    }

    //含泛型的静态方法
    public static <S> void method1(S s){
        System.out.println(s);
    }

    public static void main(String[] args) {
        GenericMethod method = new GenericMethod();
        Class<User> method1 = method.method(User.class);
        method1.getName();
        method1(Order.class);
    }
}
