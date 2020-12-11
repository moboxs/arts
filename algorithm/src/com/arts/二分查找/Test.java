package com.arts.二分查找;

public class Test {

    public int binarySearch(int target) {
        int left = 0, right = 0;
        int[] array = new int[100];
        while (left <= right) {
            int mid = (left + right) / 2;
            if (array[mid] == target) {
                return left;
            } else if (array[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
