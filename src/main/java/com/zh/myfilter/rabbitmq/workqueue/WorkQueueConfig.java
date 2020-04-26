package com.zh.myfilter.rabbitmq.workqueue;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkQueueConfig {
    @Bean
    public Queue workQueue(){
        return new Queue("q_work");
    }
}
