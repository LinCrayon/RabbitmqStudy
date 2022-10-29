package com.lsq.boot.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author linshengqian
 * @Date 2022/8/28 23:35
 * @description:
 */
@Component
public class TopicCustomer {
    //一个消费者
    @RabbitListener(bindings ={
            @QueueBinding(
                    value = @Queue, //创建临时队列
                    exchange = @Exchange(value = "topics",type = "topic" ),   //绑定的交换机
                    key = {"user.save" ,"user.*"}
            )
    } )
    public void receive1(String message){
        System.out.println("message1 =" + message);
    }


    //一个消费者
    @RabbitListener(bindings ={
            @QueueBinding(
                    value = @Queue, //创建临时队列
                    exchange = @Exchange(value = "topics",type = "topic" ),   //绑定的交换机
                    key = {"order.#","product.#" ,"user.*"}
            )
    } )
    public void receive2(String message){
        System.out.println("message2 =" + message);
    }

}
