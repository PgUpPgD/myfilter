package com.zh.myfilter.dao;

import com.zh.myfilter.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    List<UserEntity> findAll();
    UserEntity findOne(String name);
}
