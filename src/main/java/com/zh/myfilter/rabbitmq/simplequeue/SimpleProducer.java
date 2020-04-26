package com.zh.myfilter.rabbitmq.simplequeue;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SimpleProducer {

    //操作消息队列（消息生产者）
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String str = sdf.format(new Date());
        amqpTemplate.convertAndSend("q_simple", str);
    }

}
