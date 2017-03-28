import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

/**
 * Created by Lankidd on 2017/3/13.
 */
public class RabbitMQDemo {

    @Test
    public void test1() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("ruby");
        factory.setPassword("ruby");
        factory.setVirtualHost("/");
        factory.setHost("192.168.38.129");
        factory.setPort(5672);
        try (Connection conn = factory.newConnection()) {
            Channel channel = conn.createChannel();
            channel.exchangeDeclare("log-exchange", BuiltinExchangeType.TOPIC, true);
            String queue = channel.queueDeclare().getQueue();
            channel.queueBind(queue, "log-exchange", "route-key");
            System.out.println("queue = " + queue);
            System.out.println("channel = " + channel);
            channel.close();
        }
    }
}
