package workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import utils.RabbitMQUtils;

import java.io.IOException;

/**
 * @author zmf
 * @date 2020/5/30 4:14 下午
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();
        //获取通道对象
        Channel channel = connection.createChannel();
        //通过通道声明队列
        channel.queueDeclare("work",true,false,false,null);
        for (int i = 0; i < 30; i++) {
            //生产消息
            channel.basicPublish("","work",null,(i + " hello work queue").getBytes());
        }


        //关闭资源
        RabbitMQUtils.closeConnextionAndChannel(channel,connection);
    }
    
}
