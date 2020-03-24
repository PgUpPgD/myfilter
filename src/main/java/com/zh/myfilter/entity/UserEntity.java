package com.zh.myfilter.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserEntity implements Serializable {
    private Integer uid;
    private String name;
    private Integer age;
    private String pass;
    private String imgUrl;
    private String note;
    private Double balance;
    private String salt;
}
