package com.lanxiang.jvm.lock.transfer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by lanjing on 2018/12/3.
 */
public class Account {

    private String name;

    private int balance;

    private Lock lock;

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Account(String name, int balance) {
        this.name = name;
        this.balance = balance;
        this.lock = new ReentrantLock();
    }

    public Lock getLock() {
        return lock;
    }
}
