package com.lanxiang.jersey2guice.aop;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by lanxiang on 2016/11/30.
 */

@Path(MyResource.PATH)
public class MyResource {

    public static final String PATH = "/aop-rsrc";

    public static final String RESPONSE = "Hello, World!";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @MyAnnotation
    public String sayHello() {
        return RESPONSE;
    }
}
