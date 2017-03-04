package com.lanxiang.jersey.client.rest.impl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Date;

/**
 * Created by lanxiang on 2017/3/3.
 */

@Slf4j
@Singleton
@Path("rest")
@Api(value = "restServiceImpl", description = "rest接口")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class RestServiceImpl {

    @GET
    @Path("now")
    @ApiOperation("时间")
    public Date getTime() {
        return new Date();
    }
}
