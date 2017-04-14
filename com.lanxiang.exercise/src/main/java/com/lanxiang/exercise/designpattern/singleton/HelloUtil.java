package com.lanxiang.exercise.designpattern.singleton;

/**
 * Created by lanjing on 2017/4/13.
 */
public class HelloUtil {

    private static HelloUtil instance;

    private HelloUtil() {

    }

    public static HelloUtil getInstance() {
        return Singleton.INSTANCE.getHelloUtil();
    }

    enum Singleton {

        INSTANCE;

        private HelloUtil helloUtil;

        Singleton() {
            helloUtil = new HelloUtil();
        }

        public HelloUtil getHelloUtil() {
            return this.helloUtil;
        }
    }
}
