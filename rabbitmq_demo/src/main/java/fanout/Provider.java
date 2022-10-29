package fanout;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @Author linshengqian
 * @Date 2022/8/28 16:58
 * @description: fanout 扇出 也称为广播
 */
public class Provider {


    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();


        //将通道声明指定交换机   //参数1: 交换机名称    参数2: 交换机类型  fanout 广播类型
        channel.exchangeDeclare("exchange.fanout","fanout");

        //发送消息
        //参数1: 交换机名称 参数2:队列名称  参数3:传递消息额外设置  参数4:消息的具体内容
        channel.basicPublish("exchange.fanout","", MessageProperties.PERSISTENT_TEXT_PLAIN,"fanout type message".getBytes());

        //释放资源
        RabbitMQUtils.closeConnectionAndChanel(channel,connection);

    }
}
