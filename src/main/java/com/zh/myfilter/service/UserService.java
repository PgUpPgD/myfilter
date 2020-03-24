package com.zh.myfilter.service;

import com.zh.myfilter.entity.UserEntity;

import java.util.List;

public interface UserService {
    List<UserEntity> findAll(Integer page, Integer limit);
    UserEntity findByUserName(String name);
    List<String> findPermsByName(String name);
    int userSignIn(String name, String pass);
    int updateUser(UserEntity user);
    String transfer(String name, Integer uid, Double subtract);
}
