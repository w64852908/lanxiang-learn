package com.lanxiang.javaadvanced.compare;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by lanxiang on 2016/12/2.
 */
public class Main {
    @Test
    public void run() {
        User a = new User(10001, "lanxiang", new Date());
        User b = new User(10002, "lilili", new Date());
        User c = new User(10003, "yooyoyo", new Date());
        User d = new User(10004, "checkcheck", new Date());
        List<User> list = Arrays.asList(d, c, a, b);
        System.out.println(list);
        UserComparator comparator = new UserComparator(13);
        Collections.sort(list, comparator);
        System.out.println(list);
    }
}
