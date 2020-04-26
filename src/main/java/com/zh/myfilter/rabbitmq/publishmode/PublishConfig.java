package com.zh.myfilter.rabbitmq.publishmode;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublishConfig {

    @Bean
    public Queue fanoutQueue1(){
        return new Queue("fanout.queue1");
    }

    @Bean
    public Queue fanoutQueue2(){
        return new Queue("fanout.queue2");
    }

    @Bean
    FanoutExchange fanoutExchange(){
        //定义四种类型之一的fanout交换机
        return new FanoutExchange("fanoutExchange");
    }

    /**
     * @return
     * 给队列绑定交换机
     */
    @Bean
    public Binding bindingQueue1(){
        return BindingBuilder.bind(fanoutQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingQueue2(){
        return BindingBuilder.bind(fanoutQueue2()).to(fanoutExchange());
    }
}
