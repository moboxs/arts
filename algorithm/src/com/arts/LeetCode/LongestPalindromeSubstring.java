package com.arts.LeetCode;

import java.util.Vector;

/**
 * 题目：找出字符串中最长的回文子串
 * 例：Input: "basbbsabd"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 */
public class LongestPalindromeSubstring {

    //
    public String longestPalindrome(String str) {
        int len = str.length();
        if (len == 0) {
            return "";
        }
        int start = 0, end = 0;
        int maxLen = 0;
        for (int i=0; i<len; i++) {
            for (int j=i+1; j<len; j++) {
                int temp1, temp2;
                for (temp1 = i, temp2 = j; temp1 < temp2; temp1++, temp2--) {
                    if (str.charAt(temp1) != str.charAt(temp2)) {
                        break;
                    }
                }
                if (temp1 >= temp2 && j-i > maxLen) {
                    maxLen = j-i+1;
                    start = i;
                    end = j;
                }
            }
        }
        if (maxLen > 0) {
            return str.substring(start, maxLen);
        }
        return "";
    }

    public String longestPalindrome2(String str) {
        int dp[][] = new int[str.length()][str.length()];
        int left = 0, right = 0, maxLen = 0;
        for (int i=0; i<str.length(); i++) {
            for (int j=0; j<i; j++) {
                //dp[j][i] = (str.charAt(i) == str.charAt(j) && ((i-j < 2) || dp[j+1][i-1] == 1));
            }
        }
        return "";
    }

    public static void main(String[] args){
        LongestPalindromeSubstring lp = new LongestPalindromeSubstring();
        String ss = "abdbajdsbcdefedcb";
        System.out.println(lp.longestPalindrome(ss));
    }
}
