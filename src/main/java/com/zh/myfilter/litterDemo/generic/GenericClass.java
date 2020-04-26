package com.zh.myfilter.litterDemo.generic;

import lombok.Data;

@Data
public class GenericClass<T> {  //泛型类
    private T name;
    private T age;

    public static void main(String[] args) {
        GenericClass<Object> aClass = new GenericClass<>();
        aClass.setName("tom");
    }
}
