import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;

public class ConsumerMain {
    public static void main(String[] args) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(KafkaConfig.consumerProperties());
        String topic = "topic_1";
        //订阅topic
        consumer.subscribe(Collections.singletonList(topic));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(1000);
            records.forEach(ConsumerMain::accept);
        }
    }

    private static void accept(ConsumerRecord<String, String> record) {
        System.out.printf("topic = %s, partition=%d, offset=%d, key=%s, value=%s", record.topic(),
                record.partition(), record.offset(), record.key(), record.value());
    }
}
