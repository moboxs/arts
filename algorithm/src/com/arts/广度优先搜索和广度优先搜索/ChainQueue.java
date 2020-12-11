package com.arts.广度优先搜索和广度优先搜索;

public class ChainQueue {
    private QueueNode head;
    private QueueNode tail;
    private int size;

    public ChainQueue() {}

    public void insert(QueueNode node) {
        if (head == null) {
            head = node;
            tail = head;
        } else {
            node.next = tail;
            tail.prev = node;
            tail = node;
        }
        size++;
    }

    public QueueNode remove() {
        if (isEmpty()) {
            System.out.println("队列为空");
            return null;
        } else {
            QueueNode temp = head;
            head = head.prev;
            size--;
            return temp;
        }
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public QueueNode peek() {
        if (!isEmpty()) {
            return head;
        } else {
            System.out.println("队列为空");
            return null;
        }
    }

    public int size() {
        return size;
    }

    public void print() {
        QueueNode temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.prev;
        }
        System.out.println();
    }
}
