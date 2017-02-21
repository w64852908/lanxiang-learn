package com.lanxiang.jvm.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lanxiang on 2017/2/18.
 */
public class DynamicProxyTest {

    interface IHello {
        String sayHello();
    }

    static class Hello implements IHello {

        @Override
        public String sayHello() {
            System.out.println("Hello World");
            return "lanxiang";
        }
    }

    static class DynamicProxy implements InvocationHandler {

        Object originalObj;

        Object bind(Object originalObj) {
            this.originalObj = originalObj;
            return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(), originalObj.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(originalObj, args);
        }
    }

    @Test
    public void run() {
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        System.out.println(hello.sayHello());
    }
}
