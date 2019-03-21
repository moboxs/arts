package com.arts.LeetCode;

/**
 * 查找两个有序数组的中位数
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * The median is (2 + 3)/2 = 2.5
 */
public class MedianSortedArrays {

    public double findMedianOfSortedArrays(int[] arr1, int[] arr2) {
        //merge sort
        int len1 = arr1.length;
        int len2 = arr2.length;
        int m = len1 + len2;
        int mid = (m + 1) / 2 - 1;
        int[] mergeArr = new int[m];
        for (int i=0,j=0,k=0; i<m; i++) {
            if (j<len1 && k<len2) {
                if (arr1[j] < arr2[k]) {
                    mergeArr[i] = arr1[j++];
                } else {
                    mergeArr[i] = arr2[k++];
                }
            } else {
                if (j < len1) {
                    mergeArr[i] = arr1[j++];
                }
                if (k < len2) {
                    mergeArr[i] = arr2[k++];
                }
            }
            if (i  == mid + 1) {
                break;
            }
        }
        return m % 2 == 0 ? (double)(mergeArr[mid] + mergeArr[mid+1]) / 2 : (double)mergeArr[mid];
    }
    public static void main(String[] args){
        MedianSortedArrays m = new MedianSortedArrays();
        int[] arr1 = {2,3,5,6};
        int[] arr2 = {4,6,9,10,12};
        System.out.println("===\n"+m.findMedianOfSortedArrays(arr1, arr2));

    }
}
