package com.lanxiang.designpattern.serviceLocator;

/**
 * Created by lanxiang on 2016/12/1.
 */
public class InitialContext {
    public Object lookup(String jndiName) {
        if (jndiName.equalsIgnoreCase("SERVICE1")) {
            System.out.println("Looking up and creating a new Service1 object");
            return new Service1();
        } else if (jndiName.equalsIgnoreCase("SERVICE2")) {
            System.out.println("Looking up and creating a new Service1 object");
            return new Service2();
        }
        return null;
    }
}
