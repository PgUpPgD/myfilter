package com.zh.myfilter.rabbitmq.topicmode;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
        amqpTemplate.convertAndSend("topicExchange","topic.a","01");
        amqpTemplate.convertAndSend("topicExchange","topic.a.c","02");
    }
}
