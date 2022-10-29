package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author linshengqian
 * @Date 2022/8/28 13:28
 * @description:
 */
public class Customer2 {

    public static final String QUEUE_WORK="work";

    public static void main(String[] args) throws IOException, TimeoutException {
        //通过工具类获取对象
        Connection connection = RabbitMQUtils.getConnection();
        //创建通道
        final Channel channel = connection.createChannel();

        //每次只能消费一个消息
        channel.basicQos(5);

        //通道绑定对象
        channel.queueDeclare("QUEUE_WORK",true,false,false,null);

        //消费消息
        //参数1 :消费哪个队列的消息   队列名称
        //参数2 :开始消息的自动确认机制
        //参数3: 消费时的回调接口
        channel.basicConsume("QUEUE_WORK",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2: "+new String(body));
                //手动确认 参数1：手动确认消息标识 参数2：false 每次确认一个
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        });

    }
}
