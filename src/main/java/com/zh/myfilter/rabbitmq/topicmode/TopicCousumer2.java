package com.zh.myfilter.rabbitmq.topicmode;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.b")
public class TopicCousumer2 {

    @RabbitHandler
    public void receive(String msg){
        System.out.println("consumer2: " + msg);
    }
}

