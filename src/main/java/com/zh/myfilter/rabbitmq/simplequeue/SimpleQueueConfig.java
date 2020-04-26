package com.zh.myfilter.rabbitmq.simplequeue;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SimpleQueueConfig {
    /**
     * 定义String 队列
     * @return
     */
    @Bean
    public Queue stringQueue(){
        return new Queue("q_simple");
    }
}
