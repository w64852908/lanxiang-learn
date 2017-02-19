package lanxiang.designpattern.proxy.dynamic;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * Created by lanjing on 2017/2/18.
 */
public class DynamicProxyPattern {

    static interface IHello {
        void sayHello(String str);
    }

    static class Hello implements IHello {

        @Override
        public void sayHello(String str) {
            System.out.println("Hello " + str);
        }
    }

    static interface ILogger {

        void start(Method method);

        void end(Method method);
    }

    static class Logger implements ILogger {

        @Override
        public void start(Method method) {
            System.out.println(new Date() + method.getName() + " say hello start...");
        }

        @Override
        public void end(Method method) {
            System.out.println(new Date() + method.getName() + " say hello end");
        }
    }

    static class DynamicHello implements InvocationHandler {

        //调用对象
        private Object proxy;

        //目标对象
        private Object target;

        public Object bind(Object target, Object proxy) {
            this.target = target;
            this.proxy = proxy;
            return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result;
            Class<?> clazz = this.proxy.getClass();
            Method start = clazz.getDeclaredMethod("start", Method.class);
            start.invoke(this.proxy, method);
            result = method.invoke(this.target, args);
            Method end = clazz.getDeclaredMethod("end", Method.class);
            end.invoke(this.proxy, method);
            return result;
        }
    }

    @Test
    public void run(){
        IHello hello = (IHello) new DynamicHello().bind(new Hello(), new Logger());
        hello.sayHello("明天");
    }
}
