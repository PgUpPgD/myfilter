package com.zh.myfilter.rabbitmq.workqueue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "q_work")
public class WorkConsumer1 {

    @RabbitHandler
    public void recived(String msg){
        int random = (int)(Math.random() * 1000);
        try {
            Thread.sleep(1000);
        } catch(Exception e) {
        }
        System.out.println("[work1] recieved message:" + msg);
    }
}
