import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>@author chenmingyi
 * <p>@version 1.0
 * <p>date: 2019/11/20
 */
public class RocketmqTest {

    @Test
    public void testProduce() throws MQClientException {
        /*
        声明并初始化一个 Producer，同时制定Producer Group 名称
        一个应用创建一个Producer（单例），由应用来维护此对象
        Producer Group的名称需要应用保证唯一性
        Producer Group这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键
        因为服务器会查这个Group下的任意一个Producer
         */
        DefaultMQProducer producer = new DefaultMQProducer("pgroup1");

        /*
        指定 Producer 连接的nameServer 服务器所在的地址及端口
        如果是分布式部署的多个，则用 英文分号 ; 隔开
         */
        producer.setNamesrvAddr("localhost:9876");
        /*
        指定在Producer Group 中的名称
         */
        producer.setInstanceName("p1");
        /*
        Producer 对象在使用之前必须要调用start 进行启动初始化，初始化一次即可，切忌每次发送消息都初始化
         */
        producer.start();

        /*
        一个Producer 对象可以发送多个topic，多个tag 的消息
        本实例send 方法采用同步调用，只要不抛弃即成功
         */
        boolean flag1 = true, flag2 = true;
        for (int i = 0; i < 8; i++) {
            try {
                Message msg;
                if (i % 2 == 1) {
                    /*
                    创建一个消息实例，指定topic、tag和消息主体
                     */
                    msg = new Message("topic1", flag1 ? "tagA" : "tagB", ("hello rocketmq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                    flag1 = !flag1;
                } else {
                    msg = new Message("topic2", flag2 ? "tagC" : "tagD", ("hello rocketmq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                    flag2 = !flag2;
                }

                SendResult sendResult = producer.send(msg);
                System.out.println(sendResult);
            } catch (UnsupportedEncodingException | RemotingException | MQBrokerException | InterruptedException e) {
                e.printStackTrace();
            }
        }
        /*
        应用退出时，调用shutdown 关闭网络连接，清理资源，从rocketmq服务器上注销自己
        建议应用子啊tomcat等容器的退出钩子里调用 shutdown方法
         */
        producer.shutdown();
    }

    @Test
    public void testConsume() throws MQClientException, InterruptedException {
        /*
        PushConsumer 用法，感觉是消息从Rocketmq 服务器推到客户端
        实际上 PushConsumer 内部使用长轮训pull 方式从mq 服务器拉消息，然后再回调用户listener方法
         */

        /*
        声明并初始化一个consumer
        consumer group 组名，多个consumer 如果属于一个应用，订阅一样的消息，且消费逻辑一致，则应归为同一个组
        一个应用创建一个consumer，由应用来维护此对象，可以设置为全局对象或单例
        consumer group name 需要由应用保证唯一
         */
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("cgroup1");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.setInstanceName("c1");
        /*
        设置consumer 的消费策略
        CONSUME_FROM_LAST_OFFSET 默认策略，从改队列最尾开始消费，即跳过历史消息
        CONSUME_FROM_FIRST_OFFSET 从队列最开始开始消费，即历史消息（还存在broker中的）全部消费一遍
        CONSUME_FROM_TIMESTAMP 从某个时间点开始消费，和 setConsumeTimestamp() 配合使用，默认半个小时前
         */
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        /*
        消费者订阅消息
         */
        consumer.subscribe("topic1", "tagA");
        consumer.subscribe("topic2", "tagC||tagD");

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                System.out.println(Thread.currentThread().getName() + " receive : " + list.size() + " msgs");
                MessageExt msg = list.get(0);
                System.out.println(Thread.currentThread().getName() + " receive new msgs: " + list);
                System.out.println("----topic: " + msg.getTopic() + ", tag: " + msg.getTags() + ", body: " + new String(msg.getBody()));
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });


        consumer.start();
        System.out.println("consume started");
        Thread.sleep(10000);
    }
}
