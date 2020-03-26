package com.zh.myfilter.service;

import com.zh.myfilter.entity.UserEntity;
import com.zh.myfilter.utils.JsonUtil;

import java.util.List;

public interface UserService {
    List<UserEntity> findAll(Integer page, Integer limit);
    UserEntity findByUserName(String name);
    List<String> findPermsByName(String name);
    int userSignIn(String name, String pass);
    int updateUser(UserEntity user);
    JsonUtil transfer(String name, Integer uid, Double subtract, Integer tid);
}
