package com.lanxiang.guava.cache.basic;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by lanxiang on 2016/10/11.
 */
public class LoadingCacheStudy {
    @Test
    public void runWithCacheLoader() throws ExecutionException, InterruptedException {
        LoadingCache<Integer, Student> studentCache = CacheBuilder.newBuilder()
                .concurrencyLevel(8)    //设置并发级别,及可以同时写缓存的线程数
                .expireAfterWrite(10, TimeUnit.SECONDS)  //写缓存后的过期时间
                .initialCapacity(10)    //设置缓存容器的初始容量
                .maximumSize(100)   //设置缓存最大容量为100,超过100之后就会按照LRU算法来移除缓存
                .recordStats()  //统计缓存的命中率
                //设置缓存的移除通知
                .removalListener(new RemovalListener<Object, Object>() {
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
                    }
                })
                //build方法中可以指定CacheLoader,在缓存不存在时通过CacheLoader的实现自动加载缓存
                .build(
                        new CacheLoader<Integer, Student>() {
                            @Override
                            public Student load(Integer key) throws Exception {
                                System.out.println("load student " + key);
                                Student student = new Student();
                                student.setId(key);
                                student.setName("name " + key);
                                return student;
                            }
                        }
                );

        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            //从缓存中得到数据,因为没有设置过缓存,所以需要通过CacheLoader加载缓存数据
            Student student = studentCache.get(random.nextInt(15) + 1);
            System.out.println(student);
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println("cache map : " + studentCache.asMap());
        System.out.println("cache stats :");
        System.out.println(studentCache.stats().toString());
    }

    @Test
    public void runWithCallable() throws ExecutionException, InterruptedException {
        Cache<Integer, Student> studentCache = CacheBuilder.newBuilder()
                .concurrencyLevel(8)    //设置并发级别,及可以同时写缓存的线程数
                .expireAfterWrite(10, TimeUnit.SECONDS)  //写缓存后的过期时间
                .initialCapacity(10)    //设置缓存容器的初始容量
                .maximumSize(100)   //设置缓存最大容量为100,超过100之后就会按照LRU算法来移除缓存
                .recordStats()  //统计缓存的命中率
                //设置缓存的移除通知
                .removalListener(new RemovalListener<Object, Object>() {
                    public void onRemoval(RemovalNotification<Object, Object> notification) {
                        System.out.println(notification.getKey() + " was removed, cause is " + notification.getCause());
                    }
                })
                .build();

        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            final int n = random.nextInt(10) + 1;
            Student student = studentCache.get(n, new Callable<Student>() {
                @Override
                public Student call() throws Exception {
                    System.out.println("load student " + n);
                    Student temp = new Student();
                    temp.setId(n);
                    temp.setName("name " + n);
                    return temp;
                }
            });
            System.out.println(student);
            TimeUnit.SECONDS.sleep(1);
        }

        System.out.println("cache map : " + studentCache.asMap());
        System.out.println("cache stats :");
        System.out.println(studentCache.stats().toString());
    }
}

class Student {

    private int id;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
