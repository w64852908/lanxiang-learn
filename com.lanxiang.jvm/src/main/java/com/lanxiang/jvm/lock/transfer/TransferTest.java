package com.lanxiang.jvm.lock.transfer;

import org.junit.Test;

import java.util.Random;

/**
 * Created by lanjing on 2018/12/3.
 */
public class TransferTest {

    private Random random = new Random();

    @Test
    public void testTransfer() {
        final Account a = new Account("a", 20);
        final Account b = new Account("b", 20);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        transRandom(a, b);
                        printAccount(a, b);
                    }
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {

        }
    }

    private void transRandom(Account a, Account b) {
        int money = random.nextInt(10) + 1;
        int option = random.nextInt(2);
        try {
            a.getLock().lock();
            b.getLock().lock();
            if (option == 0) {
                if (a.getBalance() > money) {
                    a.setBalance(a.getBalance() - money);
                    b.setBalance(b.getBalance() + money);
                }
            } else if (option == 1) {
                if (b.getBalance() > money) {
                    a.setBalance(a.getBalance() + money);
                    b.setBalance(b.getBalance() - money);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.getLock().unlock();
            b.getLock().unlock();
        }
    }

    private void printAccount(Account a, Account b) {
        System.out.println("a left : " + a.getBalance() + ", b left : " + b.getBalance());
    }
}
