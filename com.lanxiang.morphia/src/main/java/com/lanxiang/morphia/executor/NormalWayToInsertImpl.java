package com.lanxiang.morphia.executor;

import com.google.inject.Inject;
import com.lanxiang.morphia.model.Concurrent;
import org.mongodb.morphia.Datastore;

import java.util.List;


/**
 * Created by lanxiang on 16/9/22.
 */
public class NormalWayToInsertImpl{

    @Inject
    private Datastore datastore;

    public long execute(List<Concurrent> numbers) {
        long sum = 0;
        int steps = numbers.size() / 100;
        int percent = 1;
        for (Concurrent con : numbers) {
            datastore.save(con);
            sum++;
            if (sum % steps == 0) {
                System.out.println(("Task execute " + percent + "/" + 100 + "."));
                percent++;
            }
        }
        return sum;
    }
}
