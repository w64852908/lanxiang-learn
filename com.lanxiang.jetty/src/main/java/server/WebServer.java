package server;

import com.lanxiang.servlet.guice.BootServletContextListener;
import com.lanxiang.servlet.guice.GuiceWebFilter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;
import org.eclipse.jetty.webapp.WebXmlConfiguration;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by lanxiang on 2016/11/1.
 */
@Slf4j
public class WebServer {

    public static void start(int port, String webappPath) {
        log.info("Start webapp: {}", webappPath);
        String host = "localhost";
        String path = "/";
        String uri = "http://" + host + ":" + port + path;
        try {
            Server server = new Server(port);

            WebAppContext context = new WebAppContext();
            context.setDisplayName("JettyContext");
            context.setThrowUnavailableOnStartupException(true);
            context.setContextPath(path);
            context.setResourceBase(webappPath);
            context.setWelcomeFiles(new String[]{"index.json"});
            context.setConfigurations(new Configuration[]{
                    new WebXmlConfiguration(),
                    new WebInfConfiguration(),
                    //自定义资源配置
                    new ExtMetaInfConfiguration()
            });
            context.addEventListener(new BootServletContextListener());
            context.addFilter(GuiceWebFilter.class, "/*", null);

            server.setHandler(context);
            server.start();
            log.info("Application started at {}", uri);

        } catch (Exception e) {
            log.error("Http server error." + e);
        }
    }

    public static void main(String[] args) throws MalformedURLException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(".");
        String classes = url.getFile();
        File targetClassFolder = new File(classes);
        String parentPath = targetClassFolder.getParentFile().getParent();
        start(9090, parentPath + "/src/main/java/webapp");
    }

}