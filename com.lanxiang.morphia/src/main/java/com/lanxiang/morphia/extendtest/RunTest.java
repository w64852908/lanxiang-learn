package com.lanxiang.morphia.extendtest;

import com.lanxiang.morphia.common.DatastoreFactory;
import com.mongodb.MongoClient;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by lanxiang on 2017/2/23.
 */
public class RunTest {
    private Datastore datastore;

    @Before
    public void init() {
        initLocal();
    }

    private void initLocal() {
        datastore = DatastoreFactory.getInstance("local");
    }

    @Test
    public void save() {
        Folder folder = new Folder();
        folder.setType(1);
        folder.setLevel(0);
        folder.setName("普通文件夹");
        folder.setAuthority(new HashSet<>(Arrays.asList(0, 2, 3)));
        datastore.save(folder);

        ProjectFolder projectFolder = new ProjectFolder();
        projectFolder.setType(2);
        projectFolder.setLevel(1);
        projectFolder.setName("项目文件夹");
        projectFolder.setProgress(10);
        datastore.save(projectFolder);
    }

    @Test
    public void getAll() {
        List<BasicFolder> folderList = datastore.createQuery(BasicFolder.class)
                .field("level").greaterThanOrEq(0).asList();
        for (BasicFolder folder : folderList) {
            if (folder instanceof Folder) {
                System.out.println("Folder : " + folder.toString());
            } else if (folder instanceof ProjectFolder) {
                System.out.println("ProjectFolder : " + folder.toString());
            } else {
                System.out.println("Unknown Folder");
            }
        }
    }
}
