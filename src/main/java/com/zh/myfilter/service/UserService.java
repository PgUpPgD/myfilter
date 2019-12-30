package com.zh.myfilter.service;

import com.zh.myfilter.utils.JsonUtil;

public interface UserService {
    JsonUtil findAll();
    JsonUtil findOne(String name, String pass);
}
