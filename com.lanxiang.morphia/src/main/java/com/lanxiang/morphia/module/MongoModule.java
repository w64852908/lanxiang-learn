package com.lanxiang.morphia.module;

import com.google.inject.PrivateModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.lanxiang.morphia.executor.CompletionServiceExecutorImpl;
import com.lanxiang.morphia.executor.NormalWayToInsertImpl;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

/**
 * Created by lanxiang on 16/9/18.
 */
public class MongoModule extends PrivateModule {

    protected void configure() {
        bind(NormalWayToInsertImpl.class);
        expose(NormalWayToInsertImpl.class);
        bind(CompletionServiceExecutorImpl.class);
        expose(CompletionServiceExecutorImpl.class);
        expose(Datastore.class);
    }

    @Provides
    @Singleton
    Datastore provideDataStore() {
        Morphia morphia = new Morphia();
        morphia.mapPackage("com.lanxiang.morphia.model");
        return morphia.createDatastore(new MongoClient(), "files");
    }
}