package com.lanxiang.designpattern.handwrite;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * Created by lanjing on 2017/2/21.
 */
public class Exercise {

    static class LazySingleton {

        private static LazySingleton instance;

        private LazySingleton() {
        }

        public synchronized static LazySingleton getInstance() {
            if (instance == null) {
                instance = new LazySingleton();
            }
            return instance;
        }
    }

    static class EagerSingleton {

        private static EagerSingleton instance = new EagerSingleton();

        private EagerSingleton() {
        }

        public static EagerSingleton getInstance() {
            return instance;
        }
    }

    static class EnumSingleton {

        private static EnumSingleton instance;

        private EnumSingleton() {
        }

        public static EnumSingleton getInstance() {
            return Singleton.INSTANCE.getSingleton();
        }

        static enum Singleton {

            INSTANCE;

            private EnumSingleton singleton;

            private Singleton() {
                singleton = new EnumSingleton();
            }

            public EnumSingleton getSingleton() {
                return singleton;
            }
        }
    }


    @Test
    public void singletonRun() {
        LazySingleton singleton1 = LazySingleton.getInstance();
        LazySingleton singleton2 = LazySingleton.getInstance();
        System.out.println(singleton1 == singleton2);
        EagerSingleton singleton3 = EagerSingleton.getInstance();
        EagerSingleton singleton4 = EagerSingleton.getInstance();
        System.out.println(singleton3 == singleton4);
        EnumSingleton singleton5 = EnumSingleton.getInstance();
        EnumSingleton singleton6 = EnumSingleton.getInstance();
        System.out.println(singleton5 == singleton6);
    }

    static interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {
        @Override
        public void sayHello() {
            System.out.println("Hello World.");
        }
    }

    static class ProxyHello implements IHello {

        private Hello hello;

        public ProxyHello(Hello hello) {
            this.hello = hello;
        }

        @Override
        public void sayHello() {
            System.out.println("static proxy before");
            hello.sayHello();
            System.out.println("static proxy after");
        }
    }

    @Test
    public void staticProxyRun() {
        IHello hello = new ProxyHello(new Hello());
        hello.sayHello();
    }

    static interface IGreeting {
        void greet();
    }

    static class Greeting implements IGreeting {
        @Override
        public void greet() {
            System.out.println("你好");
        }
    }

    static class InvocationProxy implements InvocationHandler {

        private Object target;

        public Object bind(Object target) {
            this.target = target;
            return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result;
            System.out.println("Before dynamic proxy.");
            result = method.invoke(this.target, args);
            System.out.println("After dynamic proxy.");
            return result;
        }
    }

    @Test
    public void dynamicProxyRun() {
        IGreeting greeting = (IGreeting) new InvocationProxy().bind(new Greeting());
        greeting.greet();
    }
}
