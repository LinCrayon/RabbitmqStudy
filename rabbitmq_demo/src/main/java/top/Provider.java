package top;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author linshengqian
 * @Date 2022/8/28 20:43
 * @description:  Routing 之订阅模型-Topic
 */
public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机以及交换机类型 topic
        channel.exchangeDeclare("exchange.topics","topic");

        //发布消息
        String routekey = "user.delete";

        channel.basicPublish("exchange.topics",routekey,null,("这里是topic动态路由模型,routekey: ["+routekey+"]").getBytes());

        //关闭资源
        RabbitMQUtils.closeConnectionAndChanel(channel,connection);

    }
}
