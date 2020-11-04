import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.util.Properties;

public class KafkaTest {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("client.id", "ProducerTranscationnalExample");
        props.put("bootstrap.servers", "localhost:9092");
        props.put("transactional.id", "test-transactional");
        props.put("acks", "all");
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        String topic = "aaa";
        producer.initTransactions();
        try {
            String msg = "matt test";
            producer.beginTransaction();
            producer.send(new ProducerRecord<String, String>(topic, "0", msg.toString()));
            producer.send(new ProducerRecord<String, String>(topic, "1", msg.toString()));
            producer.send(new ProducerRecord<String, String>(topic, "2", msg.toString()));
            producer.commitTransaction();
        }
        catch (ProducerFencedException e1) {
            e1.printStackTrace();
            producer.close();
        } catch (KafkaException e2) {
            e2.printStackTrace();
            producer.abortTransaction();
        }
        producer.close();





    }

}
