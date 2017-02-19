package lanxiang.designpattern.proxy.dynamic;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * Created by lanjing on 2017/2/18.
 */
public class InvocationHandlerProxy {

    static interface IHello {
        void sayHello();
    }

    static class Hello implements IHello {

        @Override
        public void sayHello() {
            System.out.println("Hello there");
        }
    }

    static class InvocationProxy implements InvocationHandler {

        //目标对象
        private Object target;

        public Object bind(Object target) {
            this.target = target;
            return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result;
            System.out.println("Before the target method : " + new Date().getTime());
            result = method.invoke(this.target, args);
            System.out.println("After the target method : " + new Date().getTime());
            return result;
        }
    }

    @Test
    public void run() {
        IHello hello = (IHello) new InvocationProxy().bind(new Hello());
        hello.sayHello();
    }
}
