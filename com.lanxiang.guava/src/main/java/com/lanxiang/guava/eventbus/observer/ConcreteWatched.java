package com.lanxiang.guava.eventbus.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanxiang on 16/8/29.
 */
public class ConcreteWatched implements Watched{

    //存放观察者
    private List<Watcher> list = new ArrayList<Watcher>();


    public void addWatcher(Watcher watcher) {
        list.add(watcher);
    }

    public void removeWatcher(Watcher watcher) {
        list.remove(watcher);
    }

    public void notifyWatcher(String str) {
        for (Watcher watcher : list){
            watcher.update(str);
        }
    }
}
