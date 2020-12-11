package com.arts.LeetCode.搜索排序旋转数组;

/**
 * 给你一个整数数组 nums ，和一个整数 target 。
 *
 * 该整数数组原本是按升序排列，但输入时在预先未知的某个点上进行了旋转。（例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] ）。
 *
 * 请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution {

    public int search(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int l = 0, r = arr.length;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (arr[0] < arr[mid]) {
                if (arr[0] < target && target < arr[mid]) {
                    r = mid-1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (target <= arr[r-1] && target > arr[mid]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,5,0,1};
        int k = new Solution().search(arr, 0);
        System.out.println(k);
    }
}
