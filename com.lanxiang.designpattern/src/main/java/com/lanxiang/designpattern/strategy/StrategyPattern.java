package com.lanxiang.designpattern.strategy;

import org.junit.Test;

/**
 * Created by lanjing on 2017/2/20.
 */
public class StrategyPattern {

    static interface IHello {
        void sayHello(String greeting);
    }

    static class Hello {

        private IHello hello;

        public Hello(IHello hello) {
            this.hello = hello;
        }

        public void sayHello(String greeting){
            this.hello.sayHello(greeting);
        }
    }

    @Test
    public void run() {
        Hello hello = new Hello(new IHello() {
            @Override
            public void sayHello(String greeting) {
                System.out.println(greeting);
            }
        });
        hello.sayHello("你好");
    }
}
