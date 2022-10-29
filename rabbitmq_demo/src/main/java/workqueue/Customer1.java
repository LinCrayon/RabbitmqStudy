package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeoutException;

/**
 * @Author linshengqian
 * @Date 2022/8/28 13:28
 * @description:
 */
public class Customer1 {

    public static final String QUEUE_WORK="work";

    public static void main(String[] args) throws IOException, TimeoutException {
        //通过工具类获取对象
        Connection connection = RabbitMQUtils.getConnection();
        //创建通道
         final Channel channel = connection.createChannel();

        //每次只能消费一个消息
        //channel.basicQos(1);

        //通道绑定对象
        channel.queueDeclare("QUEUE_WORK",true,false,false,null);

        //消费消息
        //参数1 :消费哪个队列的消息   队列名称
        //参数2 :消息的自动确认机制 false为关
        //参数3: 消费时的回调接口

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者1:"+new String(body,"UTF-8"));
                //参数1：确认队列中那个具体消息 参数2： 是否开启多个消息同时确认
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        channel.basicConsume("QUEUE_WORK",false,consumer );
    }
}
