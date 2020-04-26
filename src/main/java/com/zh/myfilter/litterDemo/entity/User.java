package com.zh.myfilter.litterDemo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {
    private String username;
    private Integer id;
    private List<Order> orders;
}
