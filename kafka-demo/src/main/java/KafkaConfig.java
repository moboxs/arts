import java.util.Properties;

public class KafkaConfig {

    public static Properties producerProperties() {
        Properties props = new Properties();
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("client.id", "ProducerTranscationnalExample");
        props.put("bootstrap.servers", "10.222.26.63:8001,10.222.26.64:8001,10.222.26.65:8001");
        //props.put("transactional.id", "test-transactional");
        props.put("acks", "all");
        return props;
    }

    public static Properties consumerProperties() {
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.222.26.63:8001,10.222.26.64:8001,10.222.26.65:8001");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id", "group1");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset","earliest ");
        props.put("client.id", "zy_client_id");
        return props;
    }

}
