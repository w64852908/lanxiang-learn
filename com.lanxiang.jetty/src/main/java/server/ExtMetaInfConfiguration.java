package server;

import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.webapp.AbstractConfiguration;
import org.eclipse.jetty.webapp.WebAppContext;
import org.eclipse.jetty.webapp.WebInfConfiguration;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lanxiang on 2016/11/9.
 *
 * 加载swagger静态资源到服务器路径
 */
public class ExtMetaInfConfiguration extends AbstractConfiguration {

    //在WebAppContext启动时执行,配置应该发现它在后续的阶段所需要的所有资源
    @Override
    public void preConfigure(final WebAppContext context) throws Exception {
        for (String path : getStaticFolders()) {
            URL url = getClass().getResource("/META-INF/resource/" + path);
            if (url == null) {
                continue;
            }
            String str = url.toString();
            String r = str.substring(0, str.lastIndexOf("/"));
            Resource resourceDir = Resource.newResource(r);
            getDirs(context).add(resourceDir);
        }
    }

    //清理上一个阶段创建的资源,这些资源在WebAppContext的生命周期中将不再需要
    @Override
    public void postConfigure(WebAppContext context) throws Exception {
        context.setAttribute(WebInfConfiguration.RESOURCE_DIRS, null);
    }


    //加载swagger文件
    private String[] getStaticFolders() {
        return new String[]{"apidoc"};
    }

    private Set<Resource> getDirs(WebAppContext context) {
        Set<Resource> dirs = (Set<Resource>) context.getAttribute(WebInfConfiguration.RESOURCE_DIRS);
        if (dirs == null) {
            dirs = new HashSet<Resource>();
            context.setAttribute(WebInfConfiguration.RESOURCE_DIRS, dirs);
        }
        return dirs;
    }
}
