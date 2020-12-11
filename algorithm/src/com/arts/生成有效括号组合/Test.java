package com.arts.生成有效括号组合;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<String> res = new Test().generateParenthesis(10);
        res.forEach(System.out::println);
    }

    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateOneByOne("", result, n, n);
        return result;
    }

    public void generateOneByOne(String sublist, List<String> result, int left, int right) {
        if (left == 0 && right == 0) {
            result.add(sublist);
            return;
        }
        if (left > 0) {
            generateOneByOne(sublist + "(", result, left -1, right);
        }
        if (right > left) {
            generateOneByOne(sublist + ")",result, left, right - 1);
        }
    }

}
