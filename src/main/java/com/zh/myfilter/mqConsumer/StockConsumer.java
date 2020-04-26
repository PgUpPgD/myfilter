package com.zh.myfilter.mqConsumer;

import com.zh.myfilter.dao.SeckillGoodsDao;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "queue.stock1")
public class StockConsumer {

    @Autowired
    private SeckillGoodsDao seckillGoodsDao;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitHandler
    public void receive(Map<String, Object> map){

        Integer goodsId = (Integer)map.get("goodsId");
        Integer uid = (Integer)map.get("uid");

//        int v = seckillGoodsDao.callOrderProcedure(goodsId, uid);
//        if(v == 0){  //生成订单成功
//            stringRedisTemplate.opsForValue().set("sid" + uid + goodsId, "0");
//        }else if(v == 1062){       //重复购买生成订单失败
//            stringRedisTemplate.opsForValue().set("sid" + uid + goodsId, "1062");
//        }else { //其他原因失败
//            stringRedisTemplate.opsForValue().set("sid" + uid + goodsId, "1");
//        }
    }


}
