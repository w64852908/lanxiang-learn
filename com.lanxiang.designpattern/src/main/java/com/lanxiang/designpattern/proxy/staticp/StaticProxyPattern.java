package lanxiang.designpattern.proxy.staticp;

import org.junit.Test;

import java.util.Date;

/**
 * Created by lanjing on 2017/2/18.
 */
public class StaticProxyPattern {

    static interface IHello {
        void sayHello(String str);
    }

    static class Hello implements IHello {

        @Override
        public void sayHello(String str) {
            System.out.println("Hello " + str);
        }
    }

    static class ProxyHello implements IHello {

        private IHello hello;

        public ProxyHello(IHello hello) {
            this.hello = hello;
        }

        @Override
        public void sayHello(String str) {
            Logger.start();
            hello.sayHello(str);
            Logger.end();
        }
    }

    static class Logger {

        public static void start() {
            System.out.println(new Date() + " say hello start...");
        }

        public static void end() {
            System.out.println(new Date() + " say hello end");
        }
    }

    @Test
    public void run() {
        IHello hello = new ProxyHello(new Hello());
        hello.sayHello("明天");
    }
}
