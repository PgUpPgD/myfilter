package com.zh.myfilter.service.impl;

import com.zh.myfilter.common.RedisLock;
import com.zh.myfilter.dao.SeckillGoodsDao;
import com.zh.myfilter.entity.SeckillGoods;
import com.zh.myfilter.exception.MyException;
import com.zh.myfilter.service.SeckillService;
import com.zh.myfilter.utils.JsonUtil;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
//@Transactional  //悲观锁
public class SeckillServiceImpl implements SeckillService {

    @Autowired
    private SeckillGoodsDao seckillGoodsDao;
    @Autowired
    private RedisLock redisLock;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private AmqpTemplate amqpTemplate;


    @Override
    public Map<String, Object> findById(Integer id) {
        Map<String, Object> map = new HashMap<>();
        SeckillGoods seckillGoods = seckillGoodsDao.findById(id);
        if (StringUtils.isEmpty(seckillGoods)){
            throw new MyException(1,"没有此秒杀商品");
        }
        Date beginTime = seckillGoods.getBeginTime();
        Date endTime = seckillGoods.getEndTime();
        Date date = new Date();
        String url = null;
        long residue = 0;  //秒杀剩余时间
        int status = 0;    //秒杀状态 -1 未开始 0 进行中 1已结束
        //未开始
        if((beginTime.getTime() - date.getTime()) > 0){
            residue = (beginTime.getTime() - date.getTime()) / 1000;
            status = -1;
        }else if ((endTime.getTime() - date.getTime()) > 0){
            //查看库存
            Double seckillStock = seckillGoods.getSeckillStock();
            if (seckillStock > 0){
                status = 0;
                residue = 0;
                url = id + "sid";
            }else {
                status = 1;
                residue = -1;
            }
        }else {
            status = 1;
            residue = -1;
        }
        map.put("seckillGoods",seckillGoods);
        map.put("status",status);
        map.put("residue",residue);
        map.put("url",url);
        return map;
    }

    //最有效
    public JsonUtil bySeckillGoods1(Integer uid, Integer goodsId) {
        //整体逻辑加锁，所有商品，一次只能处理一件
        synchronized (SeckillServiceImpl.class){
            // 判断是否有库存
            SeckillGoods seckillGoods = seckillGoodsDao.findById(goodsId);
            Double stock = seckillGoods.getSeckillStock();
            if (stock > 0){
                //减库存
                int i = seckillGoodsDao.updateSeckillGoods(goodsId);
                //下订单
//                seckillGoodsDao.seckillGoodsOrder(uid, goodsId);
                if (i > 0){
                    return JsonUtil.setOk();
                }
            }
            return JsonUtil.setERROR();
        }
    }

    //悲观锁(要开启事务)
    public JsonUtil bySeckillGoods4(Integer uid, Integer goodsId) {

        SeckillGoods seckillGoods = seckillGoodsDao.findByIdForUpdate(goodsId);
        Double stock = seckillGoods.getSeckillStock();
        if (stock > 0){
            //减库存
            int i = seckillGoodsDao.updateSeckillGoods(goodsId);
            //下订单
//            seckillGoodsDao.seckillGoodsOrder(uid, goodsId);
            if (i > 0){
                return JsonUtil.setOk();
            }
        }
        return JsonUtil.setERROR();
    }

    //表中增加特殊字段，版本号或者时间戳   有效
    public JsonUtil bySeckillGoods3(Integer uid, Integer goodsId) {
        SeckillGoods seckillGoods = seckillGoodsDao.findById(goodsId);
        if (seckillGoods.getSeckillStock() > 0) {
            // 库存-1
            int row = seckillGoodsDao.updateUserVersion(seckillGoods.getSeckillId(), seckillGoods.getVersion());
            if(row > 0){
//                seckillGoodsDao.seckillGoodsOrder(uid, goodsId);
                return JsonUtil.setOk();
            }
        }
        return JsonUtil.setERROR();
    }

    //redis 实现分布式锁 setnx   要一直发请求，一次可能秒不完
    public JsonUtil bySeckillGood2(Integer uid, Integer goodsId) {
        String key = "goodsId" + goodsId;
        String value = String.valueOf(System.currentTimeMillis());
        boolean lock = redisLock.lock(key, value);
        SeckillGoods seckillGoods = seckillGoodsDao.findById(goodsId);
        if (lock == true){
            try{
                Double stock = seckillGoods.getSeckillStock();
                if (stock > 0){
                    //减库存
                    int i1 = seckillGoodsDao.updateSeckillGoods(goodsId);
                    if (i1 > 0){
                        return JsonUtil.setOk();
                    }
                    //下订单
//                    seckillGoodsDao.seckillGoodsOrder(uid, goodsId);
                }
                return JsonUtil.setERROR();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                redisLock.unlock(key, value);
            }
        }
        return JsonUtil.setERROR();
    }

    //预减库存（从redis中减） 好用（快）
    @Override
    public JsonUtil bySeckillGoods(Integer uid, Integer goodsId) {
        //线程安全的
        ConcurrentHashMap<String, Boolean> concurrentHashMap = new ConcurrentHashMap<>();
        Boolean bool = concurrentHashMap.get("goodsId" + goodsId);
        if (bool != null && bool == true){
            return JsonUtil.setERROR();
        }
//        Object stock1 = redisTemplate.opsForHash().get("stock", "sid" + goodsId);
        long stock = redisTemplate.opsForHash().increment("stock", "sid" + goodsId, -1);
        if (stock >= 0){
            //将相关数据发送给消息队列
            Map<String, Object> map = new HashMap<>();
            map.put("goodsId", goodsId);
            map.put("uid", uid);
            amqpTemplate.convertAndSend("queue.stock1", map);
        }
        concurrentHashMap.put("goodsId" + goodsId, true);
        return JsonUtil.setERROR();
    }

}
