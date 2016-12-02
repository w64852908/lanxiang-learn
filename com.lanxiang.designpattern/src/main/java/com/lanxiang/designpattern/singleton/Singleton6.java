package com.lanxiang.designpattern.singleton;

/**
 * Created by lanxiang on 2016/12/2.
 */

import lombok.extern.slf4j.Slf4j;

/**
 * 这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。它更简洁，自动支持序列化机制，绝对防止多次实例化。
 * 这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还自动支持序列化机制，
 * 防止反序列化重新创建新的对象，绝对防止多次实例化。不过，由于 JDK1.5 之后才加入 enum 特性，用这种方式写不免让人感觉生疏，
 * 在实际工作中，也很少用。不能通过 reflection attack 来调用私有构造方法。
 */
@Slf4j
public enum Singleton6 {

    INSTANCE;

    public void whateverMethod() {
        log.info("Build Singleton6 instance");
    }
}
