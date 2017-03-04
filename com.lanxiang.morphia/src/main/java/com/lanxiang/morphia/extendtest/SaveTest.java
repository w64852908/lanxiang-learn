package com.lanxiang.morphia.extendtest;

import com.mongodb.MongoClient;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lanxiang on 2017/2/23.
 */
public class SaveTest {
    private Datastore datastore;

    @Before
    public void init() {
        initLocal();
    }

    private void initLocal() {
        MongoClient mongo = null;
        mongo = new MongoClient("127.0.0.1", 27017);
        datastore = new Morphia().createDatastore(mongo, "files");
    }

    @Test
    public void save() {
        Folder folder = new Folder();
        folder.setType(1);
        folder.setLevel(0);
        folder.setName("普通文件夹");
        datastore.save(folder);

        ProjectFolder projectFolder = new ProjectFolder();
        projectFolder.setType(2);
        projectFolder.setLevel(1);
        projectFolder.setName("项目文件夹");
        projectFolder.setProgress(10);
        datastore.save(projectFolder);
    }
}
