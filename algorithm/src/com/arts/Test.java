package com.arts;

public class Test {

    private static volatile Test instance;

    private Test(){}

    public static Test getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (instance) {
            if (instance == null) {
                instance = new Test();
            }
        }
        return instance;
    }

    public static void main(String[] args) {

    }

    public boolean saveFile(int length, int start) {
        int total = 1000;
        int[] arr = new int[total];

        if (total < length) {
            return false;
        }


    }
}
