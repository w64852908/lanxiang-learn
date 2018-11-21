package com.lanxiang.jvm.outofmemory;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

/**
 * Created by lanjing on 2018/11/18.
 */
public class CgLibOOM {

    @Test
    public void run() {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
        }
    }
}
