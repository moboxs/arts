import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerMain {
    public static void main(String[] args) {
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(KafkaConfig.producerProperties());
        String topic = "topic_1";
        //producer.initTransactions();
        try {
            String msg = "matt test";
            //producer.beginTransaction();
            producer.send(new ProducerRecord<String, String>(topic, "0", msg.toString()));
            producer.send(new ProducerRecord<String, String>(topic, "1", msg.toString()));
            producer.send(new ProducerRecord<String, String>(topic, "2", msg.toString()));
            //producer.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            //producer.abortTransaction();
        } finally {
            producer.close();
        }
    }
}
