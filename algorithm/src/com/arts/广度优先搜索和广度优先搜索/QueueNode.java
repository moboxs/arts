package com.arts.广度优先搜索和广度优先搜索;

public class QueueNode {
    QueueNode prev;
    QueueNode next;
    int data;

    public QueueNode(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "QueueNode{" +
                "data=" + data +
                '}';
    }
}
