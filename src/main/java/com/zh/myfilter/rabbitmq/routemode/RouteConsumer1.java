package com.zh.myfilter.rabbitmq.routemode;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "direct.queue1")
public class RouteConsumer1 {
    @RabbitHandler
    public void receive(String msg){
        System.out.println("consumer1" + msg);
    }
}
