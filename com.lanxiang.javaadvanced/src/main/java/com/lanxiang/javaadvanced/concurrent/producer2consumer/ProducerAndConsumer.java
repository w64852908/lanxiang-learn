package com.lanxiang.javaadvanced.concurrent.producer2consumer;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by lanjing on 2017/3/28.
 */
public class ProducerAndConsumer {

    static class Box {

        private Stack<Integer> stack;

        private int limit;

        public Box(Stack<Integer> stack, int limit) {
            this.stack = stack;
            this.limit = limit;
        }

        public void push(Integer value) throws InterruptedException {
            synchronized (this) {
                if (stack.size() >= limit) {
                    System.out.println("Stop pushing.");
                    this.notifyAll();
                    this.wait();
                }
                Thread.sleep((int) (Math.random() * 100));
                stack.push(value);
            }
        }

        public Integer pop() throws InterruptedException {
            synchronized (this) {
                if (stack.size() == 0) {
                    System.out.println("Stop consuming.");
                    this.notifyAll();
                    this.wait();
                }
                Thread.sleep((int) (Math.random() * 300));

                return stack.pop();
            }
        }
    }

    static class Producer {

        private Box box;

        public Producer(Box box) {
            this.box = box;
        }

        public void produce() throws InterruptedException {
            int i = 0;
            while (true) {
                box.push(i);
                System.out.println("Produce : " + i);
                i++;
            }
        }
    }

    static class Consumer {
        private Box box;

        public Consumer(Box box) {
            this.box = box;
        }

        public void consume() throws InterruptedException {
            while (true) {
                System.out.println("consume : " + box.pop());
            }
        }
    }

    @Test
    public void run() throws InterruptedException {
        final Box box = new Box(new Stack<Integer>(), 10);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Producer(box).produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new Consumer(box).consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        while (Thread.activeCount() > 3) {

        }
    }
}
