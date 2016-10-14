package com.lanxiang.apachecommons.compress;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lanxiang on 16/8/22.
 */
public class CommonsCompressTest {


    @Test
    public void testZip() {
        String path = "/Users/lanxiang/Documents/ideaworkspace/lanxiang-learn/com.lanxiang.apachecommons" +
                "/src/main/resources/txts";
        File filePath = new File(path);
        File[] files = filePath.listFiles();
        String zipFilePath = "/Users/lanxiang/Documents/ideaworkspace/lanxiang-learn/com.lanxiang.apachecommons" +
                "/src/main/resources/compress.zip";
        compressFilesToZip(files, zipFilePath);
    }

    private void compressFilesToZip(File[] files, String zipFilePath) {
        if (files != null && files.length > 0) {
            if (isEndsWithZip(zipFilePath)) {
                ZipArchiveOutputStream zaos = null;
                try {
                    File zipFile = new File(zipFilePath);
                    zaos = new ZipArchiveOutputStream(zipFile);
                    zaos.setUseZip64(Zip64Mode.AsNeeded);

                    //把每个文件用ZipArchiveEntry封装
                    //再用ZipArchiveOutPutStream写到压缩文件中
                    for (File file : files) {
                        if (file != null) {
                            ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
                            zaos.putArchiveEntry(zipArchiveEntry);
                            InputStream is = null;
                            try {
                                is = new FileInputStream(file);
                                byte[] buffer = new byte[1024 * 5];
                                int len = -1;
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
                    zaos.finish();
                } catch (IOException e) {
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
            }
        }
    }

    /**
     * 判断文件名是否以.zip为后缀
     *
     * @param fileName 需要判断的文件名
     * @return 是zip文件返回true, 否则返回false
     */

    private boolean isEndsWithZip(String fileName) {
        boolean flag = false;
        if (fileName != null && !"".equals(fileName.trim())) {
            if (fileName.endsWith(".ZIP") || fileName.endsWith(".zip")) {
                flag = true;
            }
        }
        return flag;
    }
}

