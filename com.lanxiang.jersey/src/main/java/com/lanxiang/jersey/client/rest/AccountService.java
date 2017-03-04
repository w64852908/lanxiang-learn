package com.lanxiang.jersey.client.rest;

import com.lanxiang.jersey.client.model.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by lanxiang on 2017/3/4.
 */
@Path("account")
@Api(value = "account service", description = "账号服务")
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public interface AccountService {

    @GET
    @Path("active/account")
    @ApiOperation("返回所有账户")
    List<Account> getActiveAccounts(@QueryParam("tenantId") Long tenantId);

    @GET
    @Path("baidu")
    @ApiOperation("返回百度首页的数据")
    String getBaiduIndexAsString();

    @POST
    @Path("create/account")
    @ApiOperation("创建用户")
    List<Account> create(@ApiParam Account account);
}
