package com.lanxiang.morphia.executor;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.lanxiang.morphia.model.Concurrent;
import com.lanxiang.morphia.module.MongoModule;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanxiang on 16/9/22.
 */
public class Compare {

    private List<Concurrent> numbers;

    @Inject
    private NormalWayToInsertImpl normalWayToInsert;

    @Inject
    private CompletionServiceExecutorImpl completionServiceExecutor;

    @Before
    public void init() {
        normalWayToInsert = Guice.createInjector(new MongoModule()).getInstance(NormalWayToInsertImpl.class);
        completionServiceExecutor = Guice.createInjector(new MongoModule()).getInstance(CompletionServiceExecutorImpl.class);
        numbers = new ArrayList<Concurrent>();

        for (long i = 0; i < 50000; i++) {
            numbers.add(new Concurrent(i));
        }
        System.out.println(("Initialize " + numbers.size() + " concurrents."));
    }

    @Test
    public void normal() {
        long startTime = System.currentTimeMillis();
        normalWayToInsert.execute(numbers);
        long endTime = System.currentTimeMillis();
        System.out.println(("Finish by single thread, cost " + ((endTime - startTime) / 1000 < 1 ? 1 : (endTime - startTime) / 1000) + " seconds."));
    }

    @Test
    public void fast() {
        long startTime = System.currentTimeMillis();
        completionServiceExecutor.execute(numbers);
        long endTime = System.currentTimeMillis();
        System.out.println(("Finish by 4 thread, cost " + ((endTime - startTime) / 1000 < 1 ? 1 : (endTime - startTime) / 1000) + " seconds."));
        completionServiceExecutor.close();
    }
}
