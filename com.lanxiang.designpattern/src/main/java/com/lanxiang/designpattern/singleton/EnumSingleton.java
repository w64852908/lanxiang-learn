package com.lanxiang.designpattern.singleton;

import org.junit.Test;

/**
 * Created by lanjing on 2017/2/19.
 */
public enum EnumSingleton {

    INSTANCE(10);

    private int value;

    EnumSingleton(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
