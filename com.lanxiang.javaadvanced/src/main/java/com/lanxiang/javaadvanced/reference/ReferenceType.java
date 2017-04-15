package com.lanxiang.javaadvanced.reference;

import org.junit.Test;

/**
 * Created by lanxiang on 2017/4/14.
 */
public class ReferenceType {

    @Test
    public void strongRefence() {
        Object object = new Object();
        Object[] objects = new Object[10000];
    }

    @Test
    public void softReference() {
        B b = new B();
        A a = new A(b);
    }

    @Test
    public void weakReference() {

    }
}

class A {
    B b;

    public A(B b) {
        this.b = b;
    }
}

class B {

}
