package observer;

import org.junit.Test;

/**
 * Created by lanxiang on 16/8/29.
 */
public class RunTest {

    @Test
    public void testRun(){
        Watched girl = new ConcreteWatched();

        Watcher watcher1 = new ConcreteWatcher();
        Watcher watcher2 = new ConcreteWatcher();
        Watcher watcher3 = new ConcreteWatcher();

        girl.addWatcher(watcher1);
        girl.addWatcher(watcher2);
        girl.addWatcher(watcher3);

        girl.notifyWatcher("哟哟哟");
    }
}
