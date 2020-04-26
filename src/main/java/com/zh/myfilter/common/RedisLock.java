package com.zh.myfilter.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Component
public class RedisLock {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public boolean lock(String key, String value){
        //设置超时时间
        //setIfAbsent内部调用setnx
        Boolean aBoolean = stringRedisTemplate.opsForValue().setIfAbsent(key, value);
        System.out.println(aBoolean);
        if (aBoolean){
            stringRedisTemplate.expire(key, 10,  TimeUnit.SECONDS);
            return true;
        }
        return false;
    }

    public void unlock(String key, String value){
        try{
            //设置超时时间
            String s = stringRedisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(s) && s.equals(value)){
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
