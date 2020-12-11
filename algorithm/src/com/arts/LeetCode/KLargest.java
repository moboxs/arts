package com.arts.LeetCode;

import java.util.PriorityQueue;

public class KLargest {
    final PriorityQueue<Integer> q;
    final int k;

    public KLargest(int [] arr, int k) {
        this.k = k;
        q = new PriorityQueue<>(k);
        for (int i : arr) {
            add(i);
        }
    }

    public int add(int n) {
        if (q.size() < k) {
            q.offer(n);
        } else if (q.peek() < n) {
            q.poll();
            q.offer(n);
        }
        return q.peek();
    }

    public static void main(String[] args) {
        int arr[] = {1,2,32,3,2,34,22,11,67,4,5};
        int k = 3;
        KLargest kLargest = new KLargest(arr, k);
        System.out.println(kLargest.q.peek());

    }
}
