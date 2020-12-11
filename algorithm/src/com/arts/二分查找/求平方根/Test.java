package com.arts.二分查找.求平方根;

public class Test {

    public int sqrt(int x) {
        int l=0, r = x;
        while (l <= r) {
            int mid = (l + r) / 2;
            int y = mid * mid;
            if (y > x) {
                r = mid;
            } else if (y < x) {
                l = mid;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
