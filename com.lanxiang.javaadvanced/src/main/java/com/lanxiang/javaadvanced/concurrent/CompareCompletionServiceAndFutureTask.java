package com.lanxiang.javaadvanced.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by lanjing on 2017/3/29.
 */
public class CompareCompletionServiceAndFutureTask {

    static class Task implements Callable<String> {

        private int i;

        public Task(int i) {
            this.i = i;
        }

        @Override
        public String call() throws Exception {
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName());
            return "任务 : " + i;
        }
    }

    private void testFutureTask() throws Exception {
        System.out.println("FutureTask begin");
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<String>> result = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> task = executorService.submit(new Task(i));
            result.add(task);
        }
        executorService.shutdown();
        for (int i = 0; i < 10; i++) {
            System.out.println(result.get(i).get());
        }
        System.out.println("FutureTask end");
    }

    private void testCompletionService() throws Exception {
        System.out.println("CompletionService begin");
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);
        for (int i = 0; i < 10; i++) {
            completionService.submit(new Task(i));
        }
        executorService.shutdown();
        for (int i = 0; i < 10; i++) {
            System.out.println(completionService.take().get());
        }
        System.out.println("CompletionService end");
    }

    @Test
    public void run() throws Exception {
        testFutureTask();
        System.out.println("===============================");
        testCompletionService();
    }
}
