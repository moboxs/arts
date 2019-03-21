package ProducerAndConsumer;

public class Consumer implements Runnable {

    private Content content;

    public Consumer(Content content) {
        this.content = content;
    }

    @Override
    public void run() {
        for (int i=0; i<6; i++) {
            try {
                content.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
