package observer;

/**
 * Created by lanxiang on 16/8/29.
 */
public class ConcreteWatcher implements Watcher {

    public void update(String str) {
        System.out.println(this.toString() + " : " +str);
    }

}
