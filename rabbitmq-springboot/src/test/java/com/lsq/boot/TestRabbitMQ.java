package com.lsq.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author linshengqian
 * @Date 2022/8/28 22:30
 * @description:
 */
@SpringBootTest(classes = RabbitmqSpringbootApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQ {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //topic 动态路由 订阅模式
    @Test
    public void testTopic(){
        rabbitTemplate.convertAndSend("topics","user.save","user.save 路由信息");
    }



    //route 路由
    @Test
    public void testRoute(){
        rabbitTemplate.convertAndSend("directs","info","info 的日志信息");
    }


    //fanout 广播
    @Test
    public void testFanout(){
        rabbitTemplate.convertAndSend("logs","","Fanout的模型发送的消息");
    }


    //work
    @Test
    public void testWork(){
        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend("work","work 模型"+i);
        }
    }


    //Hello World
    @Test
    public void testHello(){
        rabbitTemplate.convertAndSend("hello","Hello World");
    }

}
