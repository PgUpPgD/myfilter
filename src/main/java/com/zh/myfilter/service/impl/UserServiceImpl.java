package com.zh.myfilter.service.impl;

import com.github.pagehelper.PageHelper;
import com.zh.myfilter.common.CodeMsg;
import com.zh.myfilter.dao.UserDao;
import com.zh.myfilter.entity.UserEntity;
import com.zh.myfilter.exception.MyException;
import com.zh.myfilter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
@CacheConfig(cacheNames = "user")  //用在类上，设置缓存的名称
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CodeMsg codeMsg;

    @Override
    public List<UserEntity> findAll(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<UserEntity> list = userDao.findAll();
        return list;
    }

    @Override
    public UserEntity findByUserName(String name) {
        UserEntity entity = userDao.findByUserName(name);
        return entity;
    }

    //进行缓存，缓存方法的返回值
    @Cacheable(key = "'perms'.concat(#name)")
    @Override
    public List<String> findPermsByName(String name) {
        return userDao.findPermsByName(name);
    }

    @Override
    public int userSignIn(String name, String pass) {
        return userDao.userSignIn(name, pass);
    }

    @Override
    public int updateUser(UserEntity user) {
        return userDao.updateUser(user);
    }

    @Override
    public String transfer(String name, Integer uid, Double subtract) {
        //余额是否足够的判断
        UserEntity userName = userDao.findByUserName(name);
        if (StringUtils.isEmpty(userName)){
            throw new MyException(1,codeMsg.getUserIsEmpty());
        }else if (userName.getUid() != uid){
            throw new MyException(1,codeMsg.getNotConsistent());
        }
        UserEntity entity = new UserEntity();

//        userDao.updateUser();
        return null;
    }
}
