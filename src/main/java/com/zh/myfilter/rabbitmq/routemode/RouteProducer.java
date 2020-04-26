package com.zh.myfilter.rabbitmq.routemode;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RouteProducer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(){
        amqpTemplate.convertAndSend("directExchange","orange","11");
        amqpTemplate.convertAndSend("directExchange","black","22");
        amqpTemplate.convertAndSend("directExchange","red","33");
    }
}
