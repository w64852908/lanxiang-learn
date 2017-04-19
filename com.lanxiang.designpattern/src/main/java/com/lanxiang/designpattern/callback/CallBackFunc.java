package com.lanxiang.designpattern.callback;

import org.junit.Test;

/**
 * Created by lanjing on 2017/2/22.
 */
public class CallBackFunc {

    static interface CallBack<E> {
        void callBack(E thing);
    }

    static class BlockA {

        public void giveAnswer(CallBack callBack) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            callBack.callBack("通过了");
        }
    }

    static class UserB implements CallBack<String> {

        private BlockA blockA;

        public UserB(BlockA blockA) {
            this.blockA = blockA;
        }

        public void receiveAnswer() {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    blockA.giveAnswer(UserB.this);
                }
            }).start();
            doOtherThings();
        }

        private void doOtherThings() {
            System.out.println("UserB处理其他事情去了");
        }

        @Override
        public void callBack(String string) {
            System.out.println("面试结果是:" + string);
        }
    }

    @Test
    public void run() {
        UserB userB = new UserB(new BlockA());
        userB.receiveAnswer();
        while (Thread.activeCount() > 2) {

        }
    }
}
