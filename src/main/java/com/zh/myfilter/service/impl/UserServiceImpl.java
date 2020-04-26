package com.zh.myfilter.service.impl;

import com.github.pagehelper.PageHelper;
import com.zh.myfilter.common.CodeMsg;
import com.zh.myfilter.dao.UserDao;
import com.zh.myfilter.entity.BankEntity;
import com.zh.myfilter.entity.UserEntity;
import com.zh.myfilter.exception.MyException;
import com.zh.myfilter.service.UserService;
import com.zh.myfilter.utils.JsonUtil;
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
    public JsonUtil transfer(String name, Integer uid, Double subtract, Integer tid) {
        //余额是否足够的判断
        UserEntity userName = userDao.findByUserName(name);
        UserEntity userEntity = userDao.findById(tid);
        if (StringUtils.isEmpty(userName)){
            throw new MyException(1,codeMsg.getUserIsEmpty());
        }else if (userName.getUid() != uid){
            throw new MyException(1,codeMsg.getNotConsistent());
        }
        //加钱
        UserEntity entity = new UserEntity();
        Double balance = userName.getBalance() + subtract;
        entity.setBalance(balance);
        entity.setUid(uid);
        int i = userDao.updateUserB(entity);
        //添加记录
        BankEntity bankEntity = new BankEntity();
        bankEntity.setBalance(balance);
        bankEntity.setUid(uid);
        bankEntity.setName(name);
        bankEntity.setAddMoney(subtract);
        int i1 = userDao.insertBank(bankEntity);
        //减钱
        UserEntity entity1 = new UserEntity();
        entity1.setBalance(userEntity.getBalance() - subtract);
        entity1.setUid(tid);
        int i2 = userDao.updateUserB(entity1);
        //添加记录
        BankEntity bankEntity1 = new BankEntity();
        bankEntity1.setName(userEntity.getName());
        bankEntity1.setBalance(userEntity.getBalance() - subtract);
        bankEntity1.setUid(tid);
        bankEntity1.setSubtract(subtract);
        int i3 = userDao.insertBank(bankEntity1);
        if ((i == 0) || (i1 == 0) || (i2 == 0) || (i3 == 0)){
            return JsonUtil.setOk(codeMsg.getAccountsError());
        }
        return JsonUtil.setOk(codeMsg.getAccountsOk());
    }
}
