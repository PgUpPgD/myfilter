package com.zh.myfilter.rabbitmq.routemode;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfig {

    @Bean
    public Queue routQueue1(){
        return new Queue("direct.queue1");
    }

    @Bean
    public Queue routQueue2(){
        return new Queue("direct.queue2");
    }

    @Bean  //direct 交换机
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange");
    }

    @Bean
    public Binding directBing1(){
        return BindingBuilder.bind(routQueue1()).to(directExchange()).with("orange");
    }

    @Bean
    public Binding directBing2(){
        return BindingBuilder.bind(routQueue2()).to(directExchange()).with("black");
    }

    @Bean
    public Binding directBinding3() {
        return BindingBuilder.bind(routQueue2()).to(directExchange()).with("red");
    }

}
