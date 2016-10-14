package com.lanxiang.javaadvanced.callback;

/**
 * Created by lanxiang on 2016/10/14.
 */
public class Run {
    public static void main(String[] args) {
        Lee lee = new Lee();

        Wang wang = new Wang(lee);

        wang.askQuestion("1 + 1 = ?");
    }
}
