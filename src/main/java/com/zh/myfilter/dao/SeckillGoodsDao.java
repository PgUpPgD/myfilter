package com.zh.myfilter.dao;

import com.zh.myfilter.entity.SeckillGoods;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeckillGoodsDao {
    SeckillGoods findById(Integer id);
    int updateSeckillGoods(Integer id);
    int updateUserVersion(Integer id, Integer version);
    SeckillGoods findByIdForUpdate(Integer id);
    SeckillGoods bySeckillGoods(Integer goodsId);
    int seckillGoodsOrder(@Param("uid")Integer uid, @Param("goodsId")Integer goodsId);
    //调用存储过程
    Integer callOrderProcedure(@Param("goodsId") Integer goodsId, @Param("uid") Integer uid);

}
