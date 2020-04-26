package com.zh.myfilter.litterDemo.entity;

import lombok.Data;

import java.util.List;

@Data
public class Rol {
    private Integer rid;
    private String roleName;
    private List<User> users;
}
