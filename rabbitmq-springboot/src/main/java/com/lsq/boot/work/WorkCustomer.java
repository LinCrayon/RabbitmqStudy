package com.lsq.boot.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author linshengqian
 * @Date 2022/8/28 22:40
 * @description:
 */
@Component
public class WorkCustomer {

    //一个消费者
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive1(String message){
        System.out.println("message1 =" + message);
    }
    //一个消费者
    @RabbitListener(queuesToDeclare = @Queue("work"))
    public void receive2(String message){
        System.out.println("message2 =" + message);
    }
}
