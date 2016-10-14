package com.lanxiang.javaadvanced.callback;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lanxiang on 2016/10/14.
 */
public class Wang implements CallBack {

    private Lee lee;

    private ExecutorService executorService;

    public Wang(Lee lee) {
        this.lee = lee;
    }

    /**
     * 小王通过这个方法去问小李的问题
     *
     * @param question
     */
    public void askQuestion(final String question) {
        askNormally(question);
        play();
    }

    //这里使用一个线程的话就是异步的
    private void askSynchronized(final String question) {
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                /**
                 * 小王调用小李中的方法,在这里注册回调接口
                 * 相当于A类调用B的方法C
                 */
                try {
                    lee.executeMessage(Wang.this, question);
                } catch (InterruptedException e) {

                }
            }
        });
    }

    private void askNormally(String question) {
        try {
            lee.executeMessage(Wang.this, question);
        } catch (InterruptedException e) {

        }
    }

    public void play() {
        System.out.println("小王先出去逛会街,小李得到答案了会打电话告诉我");
    }

    /**
     * 小李知道答案后调用此方法告诉小王,就是所谓的小王的回调方法
     */
    @Override
    public void solve(String result) {
        System.out.println("小李告诉小王的答案是--->" + result);
    }
}
