package com.arts.LeetCode;

import java.util.HashMap;
import java.util.Stack;

//有效的括号
public class ValidBrackets {

    private HashMap<Character, Character> mappings;

    public ValidBrackets(){
        this.mappings = new HashMap<>();
        this.mappings.put(')', '(');
        this.mappings.put(']', '[');
        this.mappings.put('}', '{');
    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (this.mappings.containsKey(c)) {
                char topElement = stack.empty() ? '#' : stack.pop();
                if (topElement != this.mappings.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args){
        ValidBrackets vb = new ValidBrackets();
        System.out.println(vb.isValid("[]"));
    }

}
