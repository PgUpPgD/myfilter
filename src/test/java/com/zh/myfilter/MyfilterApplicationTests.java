package com.zh.myfilter;


import com.zh.myfilter.rabbitmq.publishmode.PublishProducer;
import com.zh.myfilter.rabbitmq.routemode.RouteProducer;
import com.zh.myfilter.rabbitmq.simplequeue.SimpleProducer;
import com.zh.myfilter.rabbitmq.topicmode.TopicProducer;
import com.zh.myfilter.rabbitmq.workqueue.WorkQueueProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyfilterApplicationTests {

    @Autowired
    private SimpleProducer simpleProducer;

    @Autowired
    private WorkQueueProducer workQueueProducer;

    @Autowired
    private PublishProducer publishProducer;

    @Autowired
    private RouteProducer routeProducer;

    @Autowired
    private TopicProducer topicProducer;

    @Test
    public void testSimpleQueue(){
        for (int i = 0; i < 10; i++){
            try{
                Thread.sleep(1000);
            }catch (Exception e){

            }
            simpleProducer.send();
        }
    }

    @Test
    public void testWorkQueue(){
        for (int i = 0; i < 10; i++){
            try{
                Thread.sleep(1000);
            }catch (Exception e){

            }
            workQueueProducer.send();
        }
    }

    @Test
    public void testPublishQueue(){
        publishProducer.send();
    }

    @Test
    public void testRouteQueue(){
        routeProducer.send();
    }

    @Test
    public void testTopicQueue(){
        topicProducer.send();
    }



}
