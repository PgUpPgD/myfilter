package com.zh.myfilter.rabbitmq.simplequeue;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//消费者监听队列，监听指定的消息队列
@Component
@RabbitListener(queues = "q_simple")
public class SimpleConsumer {

    //该注解用来修饰从队列中取数据的方法，并实现接受消息之后的处理逻辑
    @RabbitHandler
    public void receive(String msg){
        System.out.println("received msg :" + msg);
    }
}
