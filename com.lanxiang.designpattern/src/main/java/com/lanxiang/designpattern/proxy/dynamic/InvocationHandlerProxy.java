package com.lanxiang.designpattern.proxy.dynamic;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * Created by lanjing on 2017/2/18.
 */
public class InvocationHandlerProxy {

    interface IHello {
        void sayHello();
    }

    class Hello implements IHello {

        @Override
        public void sayHello() {
            System.out.println("hello world");
        }
    }

    class HelloProxy implements InvocationHandler {

        private IHello hello;

        public HelloProxy(IHello hello) {
            this.hello = hello;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("welcome");
            return method.invoke(hello, args);
        }

        public IHello getProxy() {
            return (IHello) Proxy.newProxyInstance(hello.getClass().getClassLoader(), hello.getClass().getInterfaces(), this);
        }
    }

    @Test
    public void run() {
        IHello hello = new HelloProxy(new Hello()).getProxy();
        hello.sayHello();
    }
}
