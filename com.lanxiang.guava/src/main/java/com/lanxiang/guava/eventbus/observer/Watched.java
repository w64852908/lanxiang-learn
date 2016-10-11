package com.lanxiang.guava.eventbus.observer;

/**
 * Created by lanxiang on 16/8/29.
 */
//抽象主题角色,watched:被观察
public interface Watched {

    void addWatcher(Watcher watcher);

    void removeWatcher(Watcher watcher);

    void notifyWatcher(String str);

}
