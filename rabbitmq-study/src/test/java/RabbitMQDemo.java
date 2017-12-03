import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.UUID;

/**
 * RabbitMQ is a message broker. In essence, it accepts messages from producers,
 * and delivers them to consumers. In-between, it can route, buffer, and persist the
 * messages according to rules you give it.
 *
 * A queue is the name for a mailbox. It lives inside RabbitMQ. Although
 * messages flow through RabbitMQ and your applications, they can be stored only
 * inside a queue. A queue is not bound by any limits, it can store as many messages
 * as you like - it's essentially an infinite buffer. Many producers can send messages
 * that go to one queue - many consumers can try to receive data from one queue.
 *
 * ************Message acknowledgment:
 * eg:
 * QueueingConsumer consumer = new QueueingConsumer(channel);
 * boolean autoAck = false;
 * channel.basicConsume("hello", autoAck, consumer);
 * while (true) {
 * QueueingConsumer.Delivery delivery = consumer.nextDelivery();
 * //...
 * channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
 * }
 *
 * ************Message durability:
 * RabbitMQ doesn't allow you to redefine an existing queue with different parameters
 * and will return an error to any program that tries to do that. But there is a quick workaround,
 * if we've already defined a queue called hello which is not durable,we should declare a queue with different name.
 * eg:
 * boolean durable = true;
 * channel.queueDeclare("task_queue", durable, false, false, null);
 * This queueDeclare change needs to be applied to both the producer and consumer code.
 * At this point we're sure that the task_queue queue won't be lost even if RabbitMQ restarts.
 * Now we need to mark our messages as persistent - by setting MessageProperties (which implements BasicProperties) to the value PERSISTENT_TEXT_PLAIN.
 * eg:
 * import com.rabbitmq.client.MessageProperties;
 * channel.basicPublish("", "task_queue",
 * MessageProperties.PERSISTENT_TEXT_PLAIN,
 * message.getBytes());
 *
 * ************Fair dispatch:
 * In order to defeat that we can use the basicQos method with the prefetchCount = 1 setting.
 * This tells RabbitMQ not to give more than one message to a worker at a time.
 * Or, in other words, don't dispatch a new message to a worker until it has processed
 * and acknowledged the previous one. Instead, it will dispatch it to the next worker that is not still busy.
 * eg:
 * int prefetchCount = 1;
 * channel.basicQos(prefetchCount);
 *
 * Exchange is a very simple thing.
 * On one side it receives messages from producers and the other side it pushes them to queues.
 * The exchange must know exactly what to do with a message it receives.
 * Should it be appended to a particular queue? Should it be appended to many queues?
 * Or should it get discarded. The rules for that are defined by the exchange type.
 * There are a few exchange types available: direct, topic, headers and fanout.
 *
 * Topic(Total On-line Program and Information Control System 总联机程序和信息控制系统) exchange
 * Messages sent to a topic exchange can't have an arbitrary routing_key.
 * It must be a list of words, delimited by dots. The words can be anything, but usually
 * they specify some features connected to the message. A few valid routing ke
 * examples: "stock.usd.nyse", "nyse.vmw", "quick.orange.rabbit".
 * There can be as many words in the routing key as you like, up to the limit of 255 bytes.
 * * (star) can substitute for exactly one word.
 * # (hash) can substitute for zero or more words.
 *
 * Binding is the relationship between exchange and a queue.
 * We need to supply a routingKey when sending, but its value is ignored for fanout exchanges.
 *
 * Temporary queues
 * Firstly, whenever we connect to Rabbit we need a fresh, empty queue. To do this we could create a queue with a random name,
 * or, even better - let the server choose a random queue name for us.
 * Secondly, once we disconnect the consumer the queue should be automatically deleted.
 * In the Java client, when we supply no parameters to queueDeclare()
 * we create a non-durable, exclusive, autodelete queue with a generated name like amq.gen-U0srCoW8TsaXjNh73pnVAw==.
 * eg:
 * String queueName = channel.queueDeclare().getQueue();
 *
 * Remote procedure call (RPC)
 *
 * @author Lankidd
 * @date 2017/4/27
 * <p>
 * To change this templates use File | Settings | File and Code Templates | Includes .
 */
public class RabbitMQDemo {
    static Channel channel;
    static Connection connection;

    @Before
    public void setUp() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("rabbit");
        factory.setPassword("rabbit510");
        factory.setVirtualHost("/");
        factory.setHost("192.168.38.130");
        factory.setPort(5672);

        connection = factory.newConnection();
        channel = connection.createChannel();
    }

    //@AfterClass
    public static void tearDown() throws Exception {
        System.out.println("closing...");
        channel.close();
        connection.close();
    }

    @Test
    public void testConnect() throws Exception {
        channel.exchangeDeclare("log-exchange", BuiltinExchangeType.TOPIC, false);
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue, "log-exchange", "route-key");
        System.out.println("queue = " + queue);
        System.out.println("channel = " + channel);
    }

    @Test
    public void testSendAndReceive() throws Exception {
        String queueName = "simple-queue";
        simpleSend(queueName, "Hello World!");
        simpleSend(queueName, "EXIT");
        simpleReceive(queueName);
    }

    @Test
    public void testMultiConsumer() throws Exception {
        String queueName = "multy-worker-queue";
        for (int i = 0; i < 15; i++) {
            Thread thread = new Thread(() -> {
                try {
                    simpleSend(queueName, "message-" + UUID.randomUUID());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            //thread.setPriority(T);
            thread.start();
        }

        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(() -> {
                try {
                    simpleReceive(queueName);
                    System.out.println(Thread.currentThread().getName() + " sleep...");
                    Thread.sleep(500);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            System.out.println("Thread-" + i + " starts");
        }

        System.out.println(Thread.currentThread().getName() + " sleep...");
        Thread.sleep(5000);

        //simpleSend(queueName, "exit");

    }

    @Test
    public void testFanout() throws Exception {
        String exchangeName = "fanout-test";
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                try {
                    fanoutSubscribe(exchangeName);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(1000);
        fanoutPublish(exchangeName, "Hello world!");
        fanoutPublish(exchangeName, "exit");
    }

    @Test
    public void testDirect() throws Exception {
        String exchangeName = "direct-test";
        String[] routingKeys = {"black", "red", "blue"};

        Thread threadMulty = new Thread(() -> {
            try {
                directSubscribe(exchangeName, routingKeys);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadMulty.start();

        Thread threadSingle = new Thread(() -> {
            try {
                directSubscribe(exchangeName, routingKeys[0]);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadSingle.start();

        Thread.sleep(1000);
        directPublish(exchangeName, routingKeys[0], "千颜");
        directPublish(exchangeName, routingKeys[1], "一辙");
        directPublish(exchangeName, routingKeys[0], "exit");
    }

    private void directPublish(String exchangeName, String routingKey, String message) throws IOException {
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);
        channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
        System.out.println("direct publish message:" + message + " with " + routingKey);
    }

    private void directSubscribe(String exchangeName, String... bindingKeys) throws IOException, InterruptedException {
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT);
        //Temporary queues
        String queueName = channel.queueDeclare().getQueue();
        Arrays.stream(bindingKeys).forEach(r -> {
            try {
                channel.queueBind(queueName, exchangeName, r);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        System.out.println("queue " + queueName + " has known the bindingKeys:" + Arrays.toString(bindingKeys));

        QueueingConsumer consumer = new QueueingConsumer(channel);

        boolean autoAck = true;
        channel.basicConsume(queueName, autoAck, consumer);
        consumerMessage(consumer, queueName, autoAck);
    }

    private void fanoutPublish(String exchangeName, String message) throws IOException {
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT);
        channel.basicPublish(exchangeName, "", null, message.getBytes());
        System.out.println("fanout publish message:" + message);
    }

    private void fanoutSubscribe(String exchangeName) throws IOException, InterruptedException {
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.FANOUT);
        //Temporary queues
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, exchangeName, "");
        System.out.println("queue " + queueName + " is waiting for messages");
        QueueingConsumer consumer = new QueueingConsumer(channel);

        boolean autoAck = true;
        channel.basicConsume(queueName, autoAck, consumer);
        consumerMessage(consumer, queueName, autoAck);
    }

    private void simpleSend(String queueName, String message) throws IOException {
        channel.queueDeclare(queueName, false, false, false, null);
        channel.basicPublish("", queueName, null, message.getBytes());
        System.out.println("send simple message:" + message);
    }

    private void simpleReceive(String queueName) throws IOException, InterruptedException {
        channel.queueDeclare(queueName, false, false, false, null);
        QueueingConsumer consumer = new QueueingConsumer(channel);
        boolean autoAck = false;
        channel.basicConsume(queueName, autoAck, consumer);

        consumerMessage(consumer, queueName, autoAck);
    }

    private void consumerMessage(QueueingConsumer consumer, String queueName, boolean autoAck) throws InterruptedException, IOException {
        boolean flag = false;
        long begin = System.currentTimeMillis();
        while (!flag) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            String threadName = Thread.currentThread().getName();
            if ("exit".equalsIgnoreCase(message)) {
                System.out.println(threadName + " exit...");
                flag = true;
            } else if (System.currentTimeMillis() - begin > 10000) {
                System.out.println(threadName + " time out...");
                flag = true;
            } else {
                System.out.println(threadName + "--> " + queueName + " receive simple message:" + message);
            }

            if (!autoAck) {
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        }
    }
}
