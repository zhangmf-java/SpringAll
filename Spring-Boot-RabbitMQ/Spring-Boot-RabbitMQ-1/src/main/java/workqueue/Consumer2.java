package workqueue;

import com.rabbitmq.client.*;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author zmf
 * @date 2020/5/30 4:26 下午
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        final Channel channel = connection.createChannel();
        channel.basicQos(1);
        //通过通道声明队列
        channel.queueDeclare("work",true,false,false,null);

        //第二参数 自动确认机制，通常为false，业务处理完后手动确认
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("Consumer-2 " + new String(body));
                /**
                 * 参数1：确认队列中哪个具体消息
                 * 参数2：是否开启多个消息同时确认
                 */
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
