package com.lanxiang.morphia.common;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 * Created by lanxiang on 2017/3/9.
 */
public class DatastoreFactory {

    private final static String LOCAL_HOST = "127.0.0.1";

    private final static int PORT = 27017;

    private final static String dbName = "files";

    public static Datastore getInstance(String env) {
        MongoClient mongo;
        if (env.equalsIgnoreCase("local")) {
            mongo = new MongoClient(LOCAL_HOST, PORT);
        } else {
            throw new IllegalArgumentException("Can only accept dev or online.");
        }
        Morphia morphia = new Morphia();
        return morphia.createDatastore(mongo, dbName);
    }
}
