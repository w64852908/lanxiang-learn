package com.lanxiang.designpattern.exercise;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

/**
 * Created by lanxiang on 2017/4/14.
 */
public class LogPerformanceProxyTest {

    interface IMethod {
        void execute();
    }

    class CMethod implements IMethod {
        @Override
        public void execute() {
            System.out.println("Execute.");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Counter {

        private long start;

        private long end;

        public void start() {
            this.start = System.currentTimeMillis();
        }

        public void end() {
            this.end = System.currentTimeMillis();
        }

        public long getTotal() {
            return this.end - this.start;
        }
    }

    class LogPerformanceProxy implements InvocationHandler {

        /**
         * 目标对象
         */
        private Object target;

        /**
         * 目标对象需要调用的对象
         */
        private Object proxy;

        public Object bind(Object target, Object proxy) {
            this.target = target;
            this.proxy = proxy;
            return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result;
            Class<?> clazz = this.proxy.getClass();
            Method start = clazz.getDeclaredMethod("start");
            start.invoke(this.proxy);
            result = method.invoke(this.target, args);
            Method end = clazz.getDeclaredMethod("end");
            end.invoke(this.proxy);
            System.out.println("总共耗时 : " + clazz.getDeclaredMethod("getTotal").invoke(this.proxy));
            return result;
        }
    }

    @Test
    public void run() {
        IMethod method = (IMethod) new LogPerformanceProxy().bind(new CMethod(), new Counter());
        method.execute();
    }
}
