package direct;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author linshengqian
 * @Date 2022/8/28 20:28
 * @description:
 */
public class Customer1 {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        String exchangeName = "exchange.direct";

        //通道声明交换机以及交换的类型
        channel.exchangeDeclare(exchangeName,"direct");

        String queue = "QUEUE_DIRECT1";

        //创建一个队列
        //String queue = channel.queueDeclare().getQueue();
        channel.queueDeclare( queue,false,false,false,null);


        //基于route key绑定队列和交换机
        channel.queueBind(queue,exchangeName,"error");

        //获取消费的消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1: "+ new String(body));
            }
        });

    }
}

