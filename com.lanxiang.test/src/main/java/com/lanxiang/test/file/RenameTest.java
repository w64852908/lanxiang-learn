package com.lanxiang.test.file;

import org.junit.Test;

import java.io.File;

/**
 * Created by lanjing on 2017/7/3.
 */
public class RenameTest {

    @Test
    public void rename1() {
        String folderPath = "/Users/lanjing/Desktop/jdownload/rename";
        File folder = new File(folderPath);
        for (File file : folder.listFiles()) {
            File renamed = new File(folderPath + "/" + file.getName() + ".wmv");
            file.renameTo(renamed);
        }
    }
}
