package com.lanxiang.javaadvanced.reflect.method;

import org.junit.Test;

import java.lang.reflect.Method;

/**
 * Created by lanjing on 2018/12/12.
 */
public class MethodInvoke {

    @Test
    public void run() throws Exception {
        Class clazz = Class.forName("com.lanxiang.javaadvanced.reflect.method.Hello");
        Method method = clazz.getDeclaredMethod("sayHello");
        method.invoke(clazz.newInstance());
    }
}
