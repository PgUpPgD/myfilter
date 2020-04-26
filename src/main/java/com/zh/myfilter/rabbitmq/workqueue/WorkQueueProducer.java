package com.zh.myfilter.rabbitmq.workqueue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component  //创建生产者
public class WorkQueueProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(){
        String dateString = new SimpleDateFormat("YYYY-mm-DD hh:MM:ss").format(new Date());
        rabbitTemplate.convertAndSend("q_work", dateString);
    }
}
