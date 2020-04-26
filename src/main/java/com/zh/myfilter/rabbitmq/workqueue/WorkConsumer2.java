package com.zh.myfilter.rabbitmq.workqueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "q_work")
public class WorkConsumer2 {

    @RabbitHandler
    public void recived(String msg){
        System.out.println("[work2] recieved message:" + msg);
    }
}
