package run;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lanxiang on 16/8/30.
 */
public class Service {

    private static final Logger logger = LoggerFactory.getLogger(Service.class);

    private void start() throws Exception {
        try {
            if (logger.isInfoEnabled()) {
                logger.info("openPlatformNotifyService initializing");
            }
            initWebservice();
        } catch (Exception e) {
            logger.error("Exception : {}", e.getMessage());
        }
    }

    private void initWebservice() throws Exception {
        int port = 8899;
        Server server = new Server(port);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        ServletHolder sh = new ServletHolder(ServletContainer.class);
        sh.setInitParameter("com.sun.jersey.config.property.resourceConfigClass",
                "com.sun.jersey.api.core.PackagesResourceConfig");
        sh.setInitParameter("com.sun.jersey.config.property.packages", "com.lanxiang.web");
        context.addServlet(sh, "/*");
        logger.info("Server start ! ");
        server.start();
    }

    public void stop() throws Exception {

    }

    public static void main(String[] args) throws Exception {
        Service server = new Service();
        server.start();
    }
}
