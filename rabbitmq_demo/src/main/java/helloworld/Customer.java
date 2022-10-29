package helloworld;

import com.rabbitmq.client.*;
import org.junit.Test;
import utils.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author linshengqian
 * @Date 2022/8/28 13:28
 * @description:
 */
public class Customer {

    public static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws IOException, TimeoutException {

         //创建工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //设置连接rabbitmq主机
        connectionFactory.setHost("192.168.1.160");
        //设置端口号
        connectionFactory.setPort(5672);
        //设置连接那个虚拟主机
        connectionFactory.setVirtualHost("/admin");
        //设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");

        //创建连接对象
        Connection connection = connectionFactory.newConnection();


        //通过工具类获取对象
        //Connection connection = RabbitMQUtils.getConnection();
        //创建通道
        Channel channel = connection.createChannel();

        //通道绑定对象
        channel.queueDeclare("QUEUE_NAME",false,false,false,null);

        //消费消息
        //参数1 :消费哪个队列的消息   队列名称
        //参数2 :开始消息的自动确认机制
        //参数3: 消费时的回调接口
        channel.basicConsume("QUEUE_NAME",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1: "+new String(body));
            }
        });

    }
}
