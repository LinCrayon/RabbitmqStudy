package com.lsq.boot.route;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author linshengqian
 * @Date 2022/8/28 22:58
 * @description:
 */
@Component
public class RouteCustomer {

    //一个消费者
    @RabbitListener(bindings ={
            @QueueBinding(
                    value = @Queue, //创建临时队列
                    exchange = @Exchange(value = "directs",type = "direct" ),  //绑定的交换机
                    key = {"error","info","warn"}
            )
    } )
    public void receive1(String message){
        System.out.println("message1 =" + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, //创建临时队列
                    exchange = @Exchange(value = "directs",type="direct"),  //指定交换机名称和类型
                    key = {"error"}
            )
    })
    public void receive2(String message){
        System.out.println("message2=" + message);
    }
}
