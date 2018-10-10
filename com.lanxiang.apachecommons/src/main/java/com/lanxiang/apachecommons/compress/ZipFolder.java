package com.lanxiang.apachecommons.zipfolder;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by lanxiang on 2017/4/15.
 */
public class ZipFolder {

    private final static String path = "/Users/lanjing/Downloads";

    private final static String output = "/Users/lanjing/Desktop/压缩.zip";

    private final static long maxBytesPersec = 2 * 1024 * 1024;

    @Test
    public void run() throws IOException {

        ZipArchiveOutputStream zipArchiveOutputStream = new ZipArchiveOutputStream(new File(output));
        zipArchiveOutputStream.setUseZip64(Zip64Mode.AsNeeded);
        zipArchiveOutputStream.setEncoding("UTF-8");
        recursiveZipFolder(zipArchiveOutputStream, path, "");
        zipArchiveOutputStream.finish();
    }

    /**
     * @param folderPath 子目录
     * @param parentPath 压缩包里的文件需要是相对路径
     * @throws IOException
     */
    private void recursiveZipFolder(ZipArchiveOutputStream zipArchiveOutputStream, String folderPath, String parentPath) throws IOException {
        File parent = new File(folderPath);
        File[] children = parent.listFiles();
        if (children == null || children.length == 0) {
            return;
        }
        List<File> pathList = new ArrayList<>();
        for (File child : children) {
            System.out.println("{" + child.toString() + "}");
            if (child.isDirectory()) {
                pathList.add(child);
                //给压缩包添加文件夹的目录结构,如果没有对应的目录结构会解压出错
                //如果这个Entry是文件夹,则参数应该以"/"结尾,以表明是文件夹
                ZipArchiveEntry folderEntry = new ZipArchiveEntry(parentPath + child.getName() + "/");
                zipArchiveOutputStream.putArchiveEntry(folderEntry);
                //添加完一个entry,需要close掉当前entry
                zipArchiveOutputStream.closeArchiveEntry();
            } else {
                ZipArchiveEntry entry = new ZipArchiveEntry(parentPath + child.getName());
                zipArchiveOutputStream.putArchiveEntry(entry);
                //按字节把压缩文件写入到entry中
                InputStream is = null;
                try {
                    is = new FileInputStream(child);
                    byte[] buffer = new byte[1024 * 4];
                    int len;
                    long total = 0;
                    long startTime = System.currentTimeMillis();
                    while ((len = is.read(buffer)) != -1) {
                        total += len;
//                        limitSpeed(total, startTime);
                        zipArchiveOutputStream.write(buffer, 0, len);
                    }
                    zipArchiveOutputStream.closeArchiveEntry();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (is != null) {
                        try {
                            is.close();
                        } catch (Exception ignored) {

                        }
                    }
                }
            }
            zipArchiveOutputStream.flush();
        }
        for (File childPath : pathList) {
            recursiveZipFolder(zipArchiveOutputStream, childPath.getAbsolutePath(), parentPath + childPath.getName() + "/");
        }
    }

    //限制下载速度,除法时分母不能为0
    private void limitSpeed(long totalBytes, long startTime) {
        if (totalBytes == 0) {
            return;
        }
        //把毫秒转换成秒
        long currTime = (System.currentTimeMillis() - startTime) / 1000;
        if (currTime == 0) {
            return;
        }
        //如果每秒的下载速度超过2m/s,则sleep 50 ms
        if ((totalBytes / currTime) >= maxBytesPersec) {
            System.out.println("Currend download speed : " + (totalBytes / currTime));
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
