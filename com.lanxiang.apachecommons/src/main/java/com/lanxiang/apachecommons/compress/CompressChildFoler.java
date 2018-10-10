package com.lanxiang.apachecommons.compress;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lanjing on 2017/12/2.
 */
public class CompressChildFoler {

    private static final String inputPath = "/Users/lanjing/Downloads/b2";

    private static final String outPutPath = "/Users/lanjing/Downloads/benzip";

    @Before
    public void init() {
        File outPutFolder = new File(outPutPath);
        if (!outPutFolder.exists()) {
            outPutFolder.mkdir();
        }
    }

    @Test
    public void run() throws Exception {
        compressChildFolder(inputPath);
    }

    private void compressChildFolder(String parent) {
        File parentFile = new File(parent);
        File[] childs = parentFile.listFiles();
        if (null == childs || childs.length == 0) {
            return;
        }
        for (File file : childs) {
            if (file.isFile()) {
                continue;
            }
            compressFolder(file);
        }
    }

    private void compressFolder(File folder) {
        if (null == folder) {
            return;
        }
        ZipArchiveOutputStream zaos = null;
        try {
            File zipFile = new File(outPutPath + "/" + folder.getName() + ".zip");
            zaos = new ZipArchiveOutputStream(zipFile);
            zaos.setUseZip64(Zip64Mode.AsNeeded);
            File[] files = folder.listFiles();
            if (null == files || files.length == 0) {
                return;
            }
            for (File file : files) {
                if (file != null) {
                    ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
                    zaos.putArchiveEntry(zipArchiveEntry);
                    InputStream is = null;
                    try {
                        is = new FileInputStream(file);
                        byte[] buffer = new byte[1024 * 5];
                        int len;
                        while ((len = is.read(buffer)) != -1) {
                            //把缓冲区的字节写入到ZipArchiveEntry
                            zaos.write(buffer, 0, len);
                        }
                        zaos.closeArchiveEntry();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (is != null) {
                            is.close();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (zaos != null) {
                    try {
                        zaos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("done with " + folder.getName());
    }

}
