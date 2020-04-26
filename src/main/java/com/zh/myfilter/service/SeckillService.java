package com.zh.myfilter.service;

import com.zh.myfilter.utils.JsonUtil;

import java.util.Map;

public interface SeckillService {
    Map<String, Object> findById(Integer id);
    JsonUtil bySeckillGoods(Integer uid, Integer goodsId);
}
