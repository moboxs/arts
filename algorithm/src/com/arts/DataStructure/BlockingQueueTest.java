package com.arts.DataStructure;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {

    public BlockingQueueTest() {
        ArrayBlockingQueue<String> abq = new ArrayBlockingQueue<String>(100);
        abq.add("1");
        abq.add("2");
        abq.add("3");
        abq.add("4");
        try {
            abq.take();
            abq.take();
            abq.remove("9");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LinkedBlockingQueue lbq = new LinkedBlockingQueue<String>(100);
        lbq.add("1");
        lbq.add("2");
        lbq.add("3");
        lbq.add("4");
        lbq.add("5");
        lbq.poll();
        lbq.remove("3");

        Map<String, String> map = new HashMap<>();
    }
    
   public static void main(String[] args){
       BlockingQueueTest b = new BlockingQueueTest();
   } 
}
