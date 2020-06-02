package helloworld;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zmf
 * @date 2020/5/30 3:49 下午
 */
public class Consumer {
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

        channel.queueDeclare("hello",false,false,false,null);

        /**
         * 消费消息
         * 参数1：消费哪个队列的消息 队列名称
         * 参数2：开始消费消息的自动确认机制
         * 参数3：消费时的回调接口
         */
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body)="+new String(body));
            }
        });
//        channel.close();
//        connection.close();
    }
}
