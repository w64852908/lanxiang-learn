package com.lanxiang.morphia.executor;

import com.google.inject.Inject;
import com.lanxiang.morphia.model.Concurrent;
import org.mongodb.morphia.Datastore;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by lanxiang on 16/9/21.
 */
public class CompletionServiceExecutorImpl {

    private int threadNum;

    private ExecutorService executorService;

    private CompletionService<Long> completionService;

    @Inject
    private Datastore datastore;

    public CompletionServiceExecutorImpl() {
        threadNum = Runtime.getRuntime().availableProcessors();
        executorService = Executors.newFixedThreadPool(threadNum);
        completionService = new ExecutorCompletionService<Long>(executorService);
    }

    public long execute(List<Concurrent> numbers) {
        int start, end, increment;
        for (int i = 0; i < threadNum; i++) {
            increment = numbers.size() / threadNum + 1;
            start = i * increment;
            end = start + increment;
            if (end > numbers.size()) {
                end = numbers.size();
            }
            MongoInsert mongoInsert = new MongoInsert(numbers, start, end, datastore);
            if (!executorService.isShutdown()) {
                completionService.submit(mongoInsert);
            }
        }
        return getResult();
    }

    private long getResult() {
        long sum = 0;
        for (int i = 0; i < threadNum; i++) {
            try {
                sum += completionService.take().get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return sum;
    }

    public void close() {
        this.executorService.shutdown();
    }
}

class MongoInsert implements Callable<Long> {

    private List<Concurrent> concurrents;

    private int start;

    private int end;

    private Datastore datastore;

    MongoInsert(final List<Concurrent> concurrents, int start, int end, Datastore datastore) {
        this.concurrents = concurrents;
        this.start = start;
        this.end = end;
        this.datastore = datastore;
    }

    public Long call() throws Exception {
        long sum = 0;
        for (int i = start; i < end; i++) {
            datastore.save(concurrents.get(i));
            sum++;
        }
        System.out.println(Thread.currentThread().getName() + " finished, by datastore_" + datastore.hashCode() + " from " + start + " -- " + (end - 1));
        return sum;
    }
}
