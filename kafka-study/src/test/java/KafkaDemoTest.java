import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Lankidd
 * @date 2017/5/7
 * <p>
 * To change this templates use File | Settings | File and Code Templates | Includes .
 */
public class KafkaDemoTest {
    volatile boolean RUNNING = true;
    String brokerList = "192.168.47.129:9092,192.168.47.130:9092,192.168.47.131:9092";
    //static Producer<String, String> producer;
    //static {
    //    Map<String, Object> props = new HashMap<>(3);
    //    props.put("metadata.broker.list", "localhost:9092,localhost:9093,localhost:9094");
    //    props.put("serializer.class", "kafka.serializer.StringEncoder");
    //    /*request.required.acks
    //        0, which means that the producer never waits for an acknowledgement from the broker (the same behavior as 0.7).
    //    This option provides the lowest latency but the weakest durability guarantees (some data will be lost when a server fails).
    //        1, which means that the producer gets an acknowledgement after the leader replica has received the data.
    //    This option provides better durability as the client waits until the server acknowledges the request as successful
    //    (only messages that were written to the now-dead leader but not yet replicated will be lost).
    //        -1, which means that the producer gets an acknowledgement after all in-sync replicas have received the data.
    //    This option provides the best durability, we guarantee that no messages will be lost as long as at least one in sync replica remains.
    //    */
    //    props.put("request.required.acks", 1);
    //    producer = new KafkaProducer<>(props);
    //}

    @Test
    public void testSimpleProducer() throws Exception {
        Map<String, Object> props = new HashMap<>(3);
        props.put("metadata.broker.list", brokerList);
        props.put("bootstrap.servers", brokerList);
        props.put("acks", "all");
        props.put("retries ", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, Object> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 3; i++) {
            LocalDateTime now = LocalDateTime.now();
            producer.send(new ProducerRecord<>("test", Integer.toString(i), now.toString()));
            System.out.println("now = " + now);
        }
        producer.close();
    }

    @Test
    public void testSimpleConsumer() throws Exception {
        Map<String, Object> props = new HashMap<>(3);
        props.put("metadata.broker.list", brokerList);
        props.put("bootstrap.servers", brokerList);
        props.put("group.id", "test");//不同ID 可以同时订阅消息
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("test"));//订阅TOPIC
        try {
            while (RUNNING) {//轮询
                ConsumerRecords records = consumer.poll(Long.MAX_VALUE);
                Set<TopicPartition> partitions = records.partitions();
                for (TopicPartition partition : partitions) {
                    List<ConsumerRecord> partitionRecords = records.records(partition);
                    for (ConsumerRecord record : partitionRecords) {
                        //可以自定义Handler,处理对应的TOPIC消息(partitionRecords.key())
                        System.out.println(record.offset() + ": " + record.value());
                    }
                    consumer.commitSync();//同步
                }
            }
        } finally {
            consumer.close();
        }
    }
}
