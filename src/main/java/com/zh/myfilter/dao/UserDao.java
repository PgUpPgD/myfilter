package com.zh.myfilter.dao;

import com.zh.myfilter.entity.BankEntity;
import com.zh.myfilter.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao{
    List<UserEntity> findAll();
    UserEntity findByUserName(String name);
    List<String> findPermsByName(String name);
    UserEntity findById(Integer id);
    int userSignIn(String name, String pass);
    int updateUser(UserEntity user);
    int updateUserB(UserEntity user);
    int insertBank(BankEntity bank);

}
