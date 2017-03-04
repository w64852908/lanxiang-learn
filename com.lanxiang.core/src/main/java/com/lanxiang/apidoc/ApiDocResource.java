package com.lanxiang.apidoc;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.config.FilterFactory;
import io.swagger.config.Scanner;
import io.swagger.config.ScannerFactory;
import io.swagger.config.SwaggerConfig;
import io.swagger.core.filter.SpecFilter;
import io.swagger.core.filter.SwaggerSpecFilter;
import io.swagger.jaxrs.Reader;
import io.swagger.jaxrs.config.JaxrsScanner;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import io.swagger.models.Swagger;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by lanxiang on 2016/11/22.
 */

@Singleton
@Slf4j
@Path("apidoc")
@Api(value = "apidoc", description = "API文档")
public class ApiDocResource {

    private static boolean initialized = false;

    @Inject
    Swagger swagger;

    private synchronized Swagger scan(Application app) {
        Swagger swagger = null;
        Scanner scanner = ScannerFactory.getScanner();
        log.debug("using scanner " + scanner);

        if (scanner != null) {
            SwaggerSerializers.setPrettyPrint(scanner.getPrettyPrint());
            Set<Class<?>> classes = null;
            if (scanner instanceof JaxrsScanner) {
                JaxrsScanner jaxrsScanner = (JaxrsScanner) scanner;
                classes = jaxrsScanner.classesFromContext(app, null);
            } else {
                classes = scanner.classes();
            }
            if (classes != null) {
                Reader reader = new Reader(swagger);
                swagger = reader.read(classes);
                if (scanner instanceof SwaggerConfig) {
                    swagger = ((SwaggerConfig) scanner).configure(swagger);
                } else {

                }
            }
        }
        initialized = true;
        return swagger;
    }

    @GET
    @Path("json")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation("获取JSON格式的API文档")
    public Response getListingJson(@Context Application app, @Context HttpHeaders headers,
                                   @Context UriInfo uriInfo) {
        if (!initialized) {
            swagger = scan(app);
        }
        swagger.setBasePath(uriInfo.getBaseUri().getPath());
        SwaggerSpecFilter swaggerSpecFilter = FilterFactory.getFilter();
        if (swaggerSpecFilter != null) {
            SpecFilter specFilter = new SpecFilter();
            swagger = specFilter.filter(swagger, swaggerSpecFilter, getQueryParams(uriInfo.getQueryParameters()),
                    getCookies(headers), getHeaders(headers));
        }
        return Response.ok().entity(swagger).build();
    }

    private Map<String, List<String>> getQueryParams(MultivaluedMap<String, String> params) {
        Map<String, List<String>> queryMap = new HashMap<>();
        if (params != null) {
            for (String key : params.keySet()) {
                List<String> values = params.get(key);
                queryMap.put(key, values);
            }
        }
        return queryMap;
    }

    private Map<String, String> getCookies(HttpHeaders headers) {
        Map<String, String> cookieMap = new HashMap<>();
        if (headers != null) {
            for (String key : headers.getCookies().keySet()) {
                Cookie cookie = headers.getCookies().get(key);
                cookieMap.put(key, cookie.getValue());
            }
        }
        return cookieMap;
    }

    private Map<String, List<String>> getHeaders(HttpHeaders headers) {
        Map<String, List<String>> headerMap = new HashMap<>();
        if (headers != null) {
            for (String key : headers.getRequestHeaders().keySet()) {
                List<String> values = headers.getRequestHeaders().get(key);
                headerMap.put(key, values);
            }
        }
        return headerMap;
    }
}
