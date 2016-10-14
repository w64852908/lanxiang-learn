package com.lanxiang.javaadvanced.callback;

/**
 * Created by lanxiang on 2016/10/13.
 */
public interface CallBack {

    /**
     * 小李知道答案时调用该函数告诉小王,也就是回调函数
     * @param result
     */
    void solve(String result);
}
