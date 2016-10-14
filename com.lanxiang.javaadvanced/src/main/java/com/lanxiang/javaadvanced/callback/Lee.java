package com.lanxiang.javaadvanced.callback;

import java.util.concurrent.TimeUnit;

/**
 * Created by lanxiang on 2016/10/14.
 */
public class Lee {

    /**
     * 相当于B类有参数为CallBack callBack的f()
     * @param callBack
     * @param question
     */
    public void executeMessage(CallBack callBack, String question) throws InterruptedException{
        System.out.println("小王问的问题--->" + question);

        //小李做自己的事情需要很长时间
        for(int i = 0; i < 10; i++){
            System.out.println("小王正在处理一些事情");
            TimeUnit.SECONDS.sleep(1);
        }

        //小李办完事情之后想出答案是2
        String result = "答案是2";

        /**
         * 小李打电话给小王,调用小王中的方法
         * 这就相当于B类反过来调用A的方法D
         */
        callBack.solve(result);
    }
}
