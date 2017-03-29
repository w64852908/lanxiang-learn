package com.lanxiang.javaadvanced.concurrent.threadlocal;


/**
 * Created by lanjing on 2017/3/29.
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> counter = new ThreadLocal<>();

    public ThreadLocalTest(int val) {
        counter.set(val);
    }

    public void init(int val) {
        counter.set(val);
    }

    private void incrAndGet() {
        int curr = counter.get();
        System.out.println(Thread.currentThread().getName() + " get counter:" + curr);
        counter.set(curr + 1);
    }


    public static void main(String[] args) throws InterruptedException {
        final int initVal = 0;
        //此处new一个对象,虽然会初始化ThreadLocal<Integer> counter,
        // 但是只是对于主进程维护的一个变量,在其他线程使用main对象的时候,counter可能处于未初始化状态
        final ThreadLocalTest main = new ThreadLocalTest(initVal);
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //主进程创建的新线程,需要初始化自己的ThreadLocal<Integer> counter的变量副本,否则会NPE
                    main.init(initVal);
                    for (int i = 0; i < 10; i++) {
                        main.incrAndGet();
                    }
                }
            }).start();
        }
        Thread.sleep(5000);
    }
}
