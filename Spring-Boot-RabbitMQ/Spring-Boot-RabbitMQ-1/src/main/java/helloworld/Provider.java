package helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zmf
 * @date 2020/5/30 4:13 下午
 */
public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.104.223.119");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("daddychoice");
        connectionFactory.setUsername("daddychoice");
        connectionFactory.setPassword("123");
        Connection connection = connectionFactory.newConnection();

        Channel channel = connection.createChannel();
        /**
         * 通道绑定对应消息队列
         * 参数一：队列名称
         * 参数二：用来定义队列特性是否需要持久化，true 持久化队列，false 不持久化
         * 参数三：exclusive 是否独占队列 true代表独占  false代表不独占
         * 参数四：autoDelete
         */
        channel.queueDeclare("hello",false,false,false,null);

        /**
         * 发布消息
         * 参数1：交换机名称
         * 参数2：队列名称
         * 参数3：传递消息额外设置 MessageProperties.PERSISTENT_TEXT_PLAIN 可以持久化度列中的消息
         * 参数4：消息的具体内容
         */
        channel.basicPublish("","hello", null,"hello rabbitmq".getBytes());
        channel.close();
        connection.close();
    }
}
