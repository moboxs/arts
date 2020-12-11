package ProducerAndConsumer;

public class Producer implements Runnable {

    private Content content;

    public Producer(Content content){
        this.content = content;
    }

    @Override
    public void run() {
        boolean flag = true;
        for (int i=0; i<6; i++) {
            if (flag) {
                try {
                    content.set("titleA", "authorA");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = false;
            } else {
                try {
                    content.set("titleB", "authorB");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = true;
            }
        }
    }
}
