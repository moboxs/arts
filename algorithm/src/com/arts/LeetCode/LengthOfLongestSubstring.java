package com.arts.LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 题目：获取给定字符串中非重复子字符串最大长度
 * 例：Input: "abcabcbb"
 *    Output: 3
 */
public class LengthOfLongestSubstring {

    //O(2n)
    public int lengthOfLongestSubstring(String str) {
        int n = str.length();
        Set<Character> charSet = new HashSet<>();
        int i = 0, j = 0;
        int curMaxLen = 0;

        while (i < n && j < n) {
            if (!charSet.contains(str.charAt(j))) {
                charSet.add(str.charAt(j++));
                curMaxLen = Math.max(curMaxLen, j - i);
            } else {
                charSet.remove(str.charAt(i++));
            }
        }

        return curMaxLen;
    }

    //O(n)
    public int lengthOfLongestSubstring1(String str) {
        int n = str.length();
        int curMaxLen = 0;
        int begin = 0;
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i=0,j=0; j < n; j++) {
            if (charMap.containsKey(str.charAt(j))) {
                i = Math.max(charMap.get(str.charAt(j)), i);
            }
            charMap.put(str.charAt(j), j + 1);
            curMaxLen = Math.max(curMaxLen, j - i + 1);
            //记录非重复最长子字符串的开始坐标
            if (curMaxLen <= (j - i + 1)) {
                begin = i;
            }
        }
        System.out.println(begin);
        return curMaxLen;
    }

    // asscii a-z:97-122  A-Z:65-90
    public int lengthOfLongestSubstring2(String str) {
        int n = str.length();
        int curMaxLen = 0;
        int[] index = new int[128];
        for (int i=0,j=0; j < n; j++) {
            i = Math.max(index[str.charAt(j)], i);
            index[str.charAt(j)] = j + 1;
            curMaxLen = Math.max(curMaxLen, j - i + 1);
        }
        return curMaxLen;
    }

    public static void main(String[] args){
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        String str = "ttdbcasqwaastwwc312125sasdcdabcbb";
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring(str)); //0.039644693s
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring1(str)); //0.030258146s
        System.out.println(lengthOfLongestSubstring.lengthOfLongestSubstring2(str));
    }
}
