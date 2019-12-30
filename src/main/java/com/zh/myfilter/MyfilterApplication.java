package com.zh.myfilter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zh.myfilter.dao")
public class MyfilterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyfilterApplication.class, args);
    }

}
