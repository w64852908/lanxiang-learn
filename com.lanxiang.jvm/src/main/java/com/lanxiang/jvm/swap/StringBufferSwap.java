package com.lanxiang.jvm.swap;

/**
 * Created by lanjing on 2018/11/25.
 */
public class StringBufferSwap {

    public static void main(String[] args) {
        StringBuffer a = new StringBuffer("One");
        StringBuffer b = new StringBuffer("Two");
        StringBufferSwap.swap(a, b);
        System.out.println(a + "   |" + b);
    }

    static void swap(StringBuffer a, StringBuffer b) {
        a.append(" more");
        b.append("M");
        System.out.println("a执行了append之后   a:" + a + "   b:" + b);
        b = a;
        b.append("   z");
        System.out.println("swap执行完毕之后   a=" + a + "   b=" + b);
    }
}
