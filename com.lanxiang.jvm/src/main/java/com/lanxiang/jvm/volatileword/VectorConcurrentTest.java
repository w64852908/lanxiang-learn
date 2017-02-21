package com.lanxiang.jvm.volatileword;

import org.junit.Test;

import java.util.Vector;

/**
 * Created by lanxiang on 2017/2/20.
 */
public class VectorConcurrentTest {

    private static Vector<Integer> vector = new Vector<>();

    @Test
    public void run() {
        while (true) {

            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.remove(i);
                    }
                }
            });

            Thread printThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            });

            Thread printThread2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < vector.size(); i++) {
                        vector.get(i);
                    }
                }
            });

            removeThread.start();
            printThread.start();
            printThread2.start();
            while (Thread.activeCount() > 20) ;
        }
    }

    @Test
    public void run2() {
        Vector<Integer> vector = new Vector<>();
        vector.add(1);
        vector.add(2);
        vector.add(3);
        vector.add(4);
        vector.add(5);
        for (Integer i : vector) {
            if (i == 3){
                vector.remove(i);
            }
            System.out.println(i);
        }
    }
}
