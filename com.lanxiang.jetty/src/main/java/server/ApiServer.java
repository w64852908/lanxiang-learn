package server;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lanxiang on 2017/3/4.
 */
public class ApiServer extends WebServer {

    public static void main(String[] args) throws MalformedURLException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(".");
        String classes = url.getFile();
        File targetClassesFolder = new File(classes);
        String parentPath = targetClassesFolder.getParentFile().getParent();
        start(9090, parentPath + "/src/main/webapp");
    }
}
