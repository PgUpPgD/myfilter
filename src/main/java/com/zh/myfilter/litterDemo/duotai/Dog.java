package com.zh.myfilter.litterDemo.duotai;

import lombok.Data;

@Data
public class Dog {
    private String name;
    private String age;

    public Dog(){
        System.out.println("父类的无参构造执行了");
    }
    public Dog(String name, String age){
        this.name = name;
        this.age = age;
        System.out.println("父类的有参构造执行了");
    }

    public void paly(){
        System.out.println(name + "and" + age);
    }
}
