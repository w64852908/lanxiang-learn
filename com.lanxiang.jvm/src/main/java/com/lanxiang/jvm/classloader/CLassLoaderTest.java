package com.lanxiang.jvm.classloader;


import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lanxiang on 2017/2/18.
 */
public class CLassLoaderTest {


    public static void main(String[] args) throws Exception {

        ClassLoader myLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream inputStream = getClass().getResourceAsStream(fileName);
                    if (inputStream == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[inputStream.available()];
                    inputStream.read(b);

                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object object = myLoader.loadClass("com.lanxiang.jvm.classloader.CLassLoaderTest").newInstance();
        System.out.println(object.getClass());
        System.out.println(object instanceof com.lanxiang.jvm.classloader.CLassLoaderTest);
    }
}
