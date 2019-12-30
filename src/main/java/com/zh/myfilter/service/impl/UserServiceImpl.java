package com.zh.myfilter.service.impl;

import com.zh.myfilter.common.CodeMsg;
import com.zh.myfilter.dao.UserDao;
import com.zh.myfilter.entity.UserEntity;
import com.zh.myfilter.service.UserService;
import com.zh.myfilter.utils.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private CodeMsg codeMsg;

    @Override
    public JsonUtil findAll() {
        List<UserEntity> list = userDao.findAll();
        if (list.size() == 0){
            return JsonUtil.setERROR(codeMsg.getListIsEmpty());
        }
        return JsonUtil.setOk(list);
    }

    @Override
    public JsonUtil findOne(String name, String pass) {
        UserEntity entity = userDao.findOne(name);
        if (StringUtils.isEmpty(entity)){
            return JsonUtil.setERROR(codeMsg.getUserIsEmpty());
        }else if (entity.getPass().equals(pass)){
            return JsonUtil.setOk(codeMsg.getNamePassOk());
        }
        return JsonUtil.setERROR(codeMsg.getPassError());
    }
}
