package com.zh.myfilter.litterDemo.duotai;

import lombok.Data;

@Data
public class Cat extends Dog {

    private String sax;

    public Cat(){
        super();
        System.out.println("子类无参构造运行了");
    }

    public Cat(String sax, String name, String age){
        super(name,age);
        System.out.println("子类有参构造运行了" + sax);
    }

    public void eat(String name){
        System.out.println(name + "eat");
    }

    public static void ly(Dog dog){
        dog.paly();
    }

    public static void main(String[] args) {
        Cat cat = new Cat();
        cat.paly();
        System.out.println("----------------");
        Cat cat1 = new Cat("man", "tom", "25");
        cat1.paly();
        cat1.eat("kk");
        System.out.println("----------------");
        Dog dog = new Cat("man", "tom", "25");
        dog.paly();
        System.out.println("----------------");
        ly(cat);
        ly(dog);
    }
}
