import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author Lankidd
 * @date 2017/5/7
 * <p>
 * To change this template use File | Settings | File and Code Templates | Includes .
 */
public class UserKafkaProducer extends Thread {
    private final KafkaProducer<Integer, String> producer;
    private final String topic;
    private final Properties props = new Properties();

    public UserKafkaProducer(String topic) {
        props.put("metadata.broker.list", "192.168.38.130:9092");
        props.put("bootstrap.servers", "192.168.38.130:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    @Override
    public void run() {
        int messageNo = 1;
        while (true) {
            String messageStr = new String("Message_" + messageNo);
            System.out.println("Send:" + messageStr);
            producer.send(new ProducerRecord<Integer, String>(topic, messageStr));
            messageNo++;
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
