package com.lanxiang.exercise.numbertransfer;

import org.junit.Test;

/**
 * Created by lanjing on 2017/4/16.
 */


//10进制转16进制
public class Ten2Sixteen {

    public String transfer(int n) {
        if (n < 16) {
            return n + "";
        }
        StringBuilder result = new StringBuilder();
        int carry = 0;
        while (n >= 16) {
            int curr = n % 16 + carry;
            result.append(transferNumToChar(curr));
            carry = n / 16;
            n = n / 16;
        }
        if (carry != 0) {
            result.append(transferNumToChar(carry));
        }
        return result.reverse().toString();
    }

    private String transferNumToChar(int n) {
        switch (n) {
            case 10:
                return "a";
            case 11:
                return "b";
            case 12:
                return "c";
            case 13:
                return "d";
            case 14:
                return "e";
            case 15:
                return "f";
        }
        return n + "";
    }

    @Test
    public void run() {
        int n = 200;
        String standard = Integer.toHexString(n);
        System.out.println("standard : " + standard);
        String transferRes = transfer(n);
        System.out.println("transferRes : " + transferRes);
    }
}
