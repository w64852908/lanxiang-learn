package com.lanxiang.designpattern.observer;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanjing on 2017/2/22.
 */
public class ObserverPattern {

    static interface Watcher {
        void update(String str);
    }

    static interface Watched {

        void addWatcher(Watcher watcher);

        void removeWatcher(Watcher watcher);

        void notifyWatchers(String str);
    }

    static class ConcreteWatcher implements Watcher {

        @Override
        public void update(String str) {
            System.out.println(str);
        }
    }

    static class ConcreteWatched implements Watched {

        private List<Watcher> watcherList = new ArrayList<>();

        @Override
        public void addWatcher(Watcher watcher) {
            watcherList.add(watcher);
        }

        @Override
        public void removeWatcher(Watcher watcher) {
            watcherList.remove(watcher);
        }

        @Override
        public void notifyWatchers(String str) {
            for (Watcher watcher : watcherList) {
                watcher.update(str);
            }
        }
    }

    @Test
    public void run() {
        Watched girl = new ConcreteWatched();

        Watcher watcher1 = new ConcreteWatcher();
        Watcher watcher2 = new ConcreteWatcher();
        Watcher watcher3 = new ConcreteWatcher();

        girl.addWatcher(watcher1);
        girl.addWatcher(watcher2);
        girl.addWatcher(watcher3);

        girl.notifyWatchers("开心");
    }
}
