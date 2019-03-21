package ProducerAndConsumer;

import java.util.concurrent.TimeUnit;

public class Content {
    private String title;
    private String author;

    private boolean produce = true;

    public synchronized void set(String title, String author) throws InterruptedException {
        while (!produce) {
            super.wait();
        }
        this.setAuthor(author);
        TimeUnit.SECONDS.sleep(1);
        this.setTitle(title);
        System.out.println("[生产者]"+this.getAuthor()+"----->"+this.getTitle());
        produce = false;
        super.notify();
    }

    public synchronized void get() throws InterruptedException {
        while (produce) {
            super.wait();
        }
        System.out.println("[消费者]"+this.getAuthor()+"----->"+this.getTitle());
        produce = true;
        super.notify();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
