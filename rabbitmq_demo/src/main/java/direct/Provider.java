package direct;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @Author linshengqian
 * @Date 2022/8/28 20:21
 * @description:  Routing 之订阅模型-Direct(直连)
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //通过通道声明交换机     参数1：交换机名称 参数2：direct 路由模式
        channel.exchangeDeclare("exchange.direct","direct");

        String exchangeName = "exchange.direct";
        //发送消息
        String routingkey = "error";
        channel.basicPublish(exchangeName,routingkey,null,("这是direct模型发布的基于route key: ["+routingkey+"] 发送的消息").getBytes());

        //关闭资源
        RabbitMQUtils.closeConnectionAndChanel(channel,connection);

    }
}
