package com.zh.myfilter.rabbitmq.publishmode;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class PublishProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String str = sdf.format(new Date());
        //发送消息，第一个参数为交换机
        //第二个参数为：rouetKey,路由的key，这里设置为空字符串即可
        //第三个参数为，要发送的数据
        amqpTemplate.convertAndSend("fanoutExchange","", "99");
    }
}
