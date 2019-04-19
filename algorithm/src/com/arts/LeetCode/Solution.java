package com.arts.LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    //7、判断数字是否是回文
    boolean isPalindrome(int x) {
        if (x < 0 || (x/10 == 0 && x != 0)) {
            return false;
        }
        int rev = 0;
        while (x > rev) {
            int d = x % 10;
            x /= 10;
            rev = rev*10 + d;
        }
        return rev == x || x == rev/10;
    }

    //三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> list = new ArrayList<>();
        for (int i=0; i<nums.length-2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                int j = i+1, k = nums.length-1, sum = 0 - nums[i];
                while (j < k) {
                    if (nums[j] + nums[k] == sum) {
                        list.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                    while (j < k && nums[j] == nums[j+1]) j++;

                }
            }
        }
        return list;
    }



    public static void main(String[] args){
        Solution solution = new Solution();

        System.out.println("\n------判断数字是否是回文------\n");
        System.out.println(solution.isPalindrome(10));

    }
}
