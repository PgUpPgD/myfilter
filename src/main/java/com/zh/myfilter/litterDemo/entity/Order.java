package com.zh.myfilter.litterDemo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Order implements Serializable {
    private Integer oid;
    private String number;
    private Integer userid;
    private User user;
}
