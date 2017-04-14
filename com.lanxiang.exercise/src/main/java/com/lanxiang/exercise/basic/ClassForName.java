package com.lanxiang.exercise.basic;

import org.junit.Test;

import java.util.Date;

/**
 * Created by lanjing on 2017/4/12.
 */
public class ClassForName {


    @Test
    public void run() throws ClassNotFoundException {
        Class<?> clazz = Class.forName("java.util.Date", true, this.getClass().getClassLoader());
        System.out.println(clazz.equals(Date.class));
    }

    @Test
    public void run2() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Date date = (Date) Class.forName("java.util.Date", true, this.getClass().getClassLoader()).newInstance();
        System.out.println(date);

    }
}
