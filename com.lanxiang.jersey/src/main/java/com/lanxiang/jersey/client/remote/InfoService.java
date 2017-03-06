package com.lanxiang.jersey.client.remote;

import com.lanxiang.jersey.client.model.SystemInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by lanxiang on 2017/3/6.
 */

@Path("info")
@Api(value = "info", description = "各种信息")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public interface InfoService {

    @GET
    @Path("now")
    @ApiOperation("获取当前时间")
    String getCurrentTime();

    @GET
    @Path("system")
    @ApiOperation("获取系统环境")
    SystemInfo getSystemInfo();

}
