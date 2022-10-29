package com.lsq.boot.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author linshengqian
 * @Date 2022/8/28 22:51
 * @description:
 */
@Component
public class FanoutCustomer {

    //一个消费者
    @RabbitListener(bindings ={
            @QueueBinding(
                    value = @Queue, //创建临时队列
                    exchange = @Exchange(value = "logs",type = "fanout" )   //绑定的交换机
            )
    } )
    public void receive1(String message){
        System.out.println("message1 =" + message);
    }

    //一个消费者
    @RabbitListener(bindings ={
            @QueueBinding(
                    value = @Queue, //创建临时队列
                    exchange = @Exchange(value = "logs",type = "fanout" )   //绑定的交换机
            )
    } )
    public void receive2(String message){
        System.out.println("message2 =" + message);
    }

}
