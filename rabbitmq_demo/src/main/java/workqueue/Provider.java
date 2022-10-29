package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author linshengqian
 * @Date 2022/8/28 15:28
 * @description:
 */
public class Provider {

    public static final String QUEUE_WORK="work";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //通过通道声明队列
        // channel.queueDeclare("队列名称",true持久化,true独占,自动删除队列,其他参数);
        channel.queueDeclare("QUEUE_WORK", true, false, false, null);
        for (int i = 0; i < 30; i++) {
            //生产消息
            //参数1: 交换机名称 参数2:队列名称  参数3:传递消息额外设置  参数4:消息的具体内容
            channel.basicPublish("", "QUEUE_WORK", null, (i+"====>:hello work queue").getBytes());
        }
        System.out.println("发送完毕");

        RabbitMQUtils.closeConnectionAndChanel(channel,connection);
    }
}
