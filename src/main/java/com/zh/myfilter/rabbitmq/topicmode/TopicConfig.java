package com.zh.myfilter.rabbitmq.topicmode;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {
    @Bean
    public Queue topic1(){
        return new Queue("topic.a");
    }

    @Bean
    public Queue topic2(){
        return new Queue("topic.b");
    }

    @Bean
    TopicExchange topicExchange(){
        //topic交换器
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding bindingTopic(){
        return BindingBuilder.bind(topic1()).to(topicExchange()).with("topic.*");
    }

    @Bean
    public Binding bindingTopic2() {
        //本例中topicB的key为topic.#那么他只会接收topic开头的消息
        return BindingBuilder.bind(topic2()).to(topicExchange()).with("topic.#");
    }


}
