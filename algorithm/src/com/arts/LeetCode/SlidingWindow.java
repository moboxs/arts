package com.arts.LeetCode;

import java.util.Deque;
import java.util.LinkedList;
//滑动窗口第K大
public class SlidingWindow {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length;
        if (len == 0) {
            return new int[0];
        }
        int[] maxs = new int[len-k+1];
        int count=0;
        Deque<Integer> window = new LinkedList<>();
        for (int i=0; i<len; i++) {
            if (i > k && window.peekFirst() <= i-k) {
                window.pollFirst();
            }
            while (!window.isEmpty() && nums[window.peekLast()] <= nums[i]) {
                window.pollLast();
            }
            window.add(i);
            if (i >= k-1) {
                maxs[count++] = nums[window.peekFirst()];
            }
        }
        return maxs;
    }

    public static void main(String[] args){
        SlidingWindow win = new SlidingWindow();
        int[] nums = {2,-1,3,2,4,1,2,5};
        int[] maxs = win.maxSlidingWindow(nums, 3);
        System.out.println(maxs);
    }
}
