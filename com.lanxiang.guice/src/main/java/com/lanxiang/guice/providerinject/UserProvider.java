package com.lanxiang.guice.providerinject;

import com.google.inject.Provider;

/**
 * Created by lanxiang on 16/9/8.
 */
public class UserProvider implements Provider<User> {

    public User get() {
        return new User(23333, "lanxiang", "123456");
    }
}
