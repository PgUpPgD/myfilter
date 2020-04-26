package com.zh.myfilter.rabbitmq.publishmode;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.queue2")
public class PublishConsumer2 {

    @RabbitHandler
    public void receive(String msg){
        System.out.println("consumer2:" + msg);
    }
}
