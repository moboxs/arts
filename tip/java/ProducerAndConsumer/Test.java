package ProducerAndConsumer;

public class Test {
    public static void main(String[] args){
        Content content = new Content();
        Thread producer = new Thread(new Producer(content), "producer");
        Thread consumer = new Thread(new Consumer(content), "consumer");

        producer.start();
        consumer.start();
    } 
}
