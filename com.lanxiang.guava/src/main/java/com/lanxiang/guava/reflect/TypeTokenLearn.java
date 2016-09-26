package com.lanxiang.guava.reflect;

import com.google.common.reflect.TypeToken;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by lanxiang on 2016/9/24.
 */
public class TypeTokenLearn {

    @Test
    public void test1() {
        TypeToken<String> stringToken = TypeToken.of(String.class);
        TypeToken<Integer> intToken = TypeToken.of(Integer.class);
        System.out.println(stringToken.toString());
        System.out.println(intToken.toString());
    }

    @Test
    public void testGetRawTypes() {
        Set<? extends Class<?>> set = TypeToken.of(String.class).getTypes().rawTypes();
        System.out.println(set);
    }

    @Test
    public void testGetDeclaredMethod() {
        Set<? extends Class<?>> set = TypeToken.of(String.class).getTypes().rawTypes();
        for (Class<?> clazz : set) {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method.getName());
            }
        }
    }
}