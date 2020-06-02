package utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

/**
 * @author zmf
 * @date 2020/5/30 4:15 下午
 */
public class RabbitMQUtils {
    private static ConnectionFactory connectionFactory;
    private static Properties properties;
    static {
        //创建连接
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("47.104.223.119");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("daddychoice");
        connectionFactory.setUsername("daddychoice");
        connectionFactory.setPassword("123");
    }

    public static Connection getConnection(){
        try {
            return connectionFactory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void closeConnextionAndChannel(Channel channel, Connection conn){
        try {
            if (channel != null){
                channel.close();
            }
            if (conn != null){
                conn.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
