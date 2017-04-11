package com.lanxiang.exercise.reviewleet;

import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

/**
 * Created by lanjing on 2017/4/6.
 */
public class _020 {

    public boolean isValid(String s) {
        if (s.length() < 2) {
            return false;
        }
        HashMap<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            }
            if (c == ')' || c == ']' || c == '}') {
                if (stack.size() == 0) {
                    return false;
                }
                char temp = stack.pop();
                if (temp != map.get(c)) {
                    return false;
                }
            }
        }
        if (stack.size() != 0) {
            return false;
        }
        return true;
    }

    @Test
    public void run() {
        String s = "((a+b)*(a-b))";
        System.out.println(isValid(s));
    }
}
