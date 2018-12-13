package com.lanxiang.designpattern.singleton;

import org.junit.Test;

/**
 * Created by lanxiang on 2018/12/6.
 */
public class UserDAOTest {

    @Test
    public void run() {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        System.out.println(UserDAOSingleton.USER_DAO_SINGLETON.getUserDAO());
                    }
                }
            }).start();
        }
        while (Thread.activeCount() > 2) {
            
        }
    }
}
