package com.lanxiang.exercise.reviewleet;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/8.
 */

/**
 * 要求：给两个字符串数字，给出乘积，有限制。
 * <p>
 * 思路：手动进行乘法运算，记录每一位的乘积然后进行进位运算。
 */
public class _043 {

    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }
        String n1 = new StringBuilder(num1).reverse().toString();
        String n2 = new StringBuilder(num2).reverse().toString();
        int[] arr = new int[n1.length() + n2.length()];
        for (int i = 0; i < n1.length(); i++) {
            for (int j = 0; j < n2.length(); j++) {
                arr[i + j] += (n1.charAt(i) - '0') * (n2.charAt(j) - '0');
            }
        }
        int carry = 0;
        int current;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            current = arr[i] + carry;
            carry = current / 10;
            result.append(current % 10);
        }
        if (carry != 0) {
            result.append(carry);
        }
        result = result.reverse();
        int index = 0;
        while (result.charAt(index) == '0') {
            index++;
        }
        return result.substring(index);
    }

    @Test
    public void run() {
        String s1 = "25";
        String s2 = "25";
        System.out.println(multiply(s1, s2));
    }
}
