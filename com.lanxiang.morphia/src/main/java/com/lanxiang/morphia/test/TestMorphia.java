package com.lanxiang.morphia.test;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.lanxiang.morphia.model.UserInfo;
import com.lanxiang.morphia.module.MongoModule;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import java.util.List;


/**
 * Created by lanxiang on 16/9/18.
 */
public class TestMorphia {

    @Inject
    private Datastore datastore;

    private final static String _id = "57ce83857c491c30f144b36d";

    @Before
    public void init() {
        datastore = Guice.createInjector(new MongoModule()).getInstance(Datastore.class);
    }

    @Test
    public void testQuery() {
        UserInfo userInfo;
        Query<UserInfo> query = datastore.createQuery(UserInfo.class)
                .field("_id").equal(new ObjectId(_id));
        userInfo = query.get();
        System.out.println(userInfo.toString());
    }

    @Test
    public void testRetrievedFields() {
        Query<UserInfo> query = datastore.createQuery(UserInfo.class)
                .retrievedFields(true, "email");
        List<UserInfo> userInfos = query.asList();
        for (UserInfo ui : userInfos) {
            System.out.println(ui.getEmail());
        }
    }

    @Test
    public void testFilter() {
        Query<UserInfo> query = datastore.createQuery(UserInfo.class)
                .filter("status = ", 0);
        List<UserInfo> userInfos = query.asList();
        System.out.println(userInfos.size());
    }

    @Test
    public void testQueryAndInsert() {
        Query<UserInfo> query = datastore.createQuery(UserInfo.class)
                .field("_id").equal(new ObjectId(_id));
        UserInfo userInfo = query.get();
        userInfo.setId(null);
        userInfo.setPhone("18615351031");
        datastore.save(userInfo);
    }
}
