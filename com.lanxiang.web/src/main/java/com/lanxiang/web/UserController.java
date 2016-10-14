package com.lanxiang.web;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.lanxiang.guice.ServiceModule;
import com.lanxiang.model.Response;
import com.lanxiang.model.User;
import com.lanxiang.model.dto.UserDTO;
import com.lanxiang.service.UserService;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by lanxiang on 16/8/31.
 */

@Path("/user")
@Singleton
@Slf4j
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
public class UserController {

    private UserService userService;

    @PostConstruct
    public void init() {
        userService = Guice.createInjector(new ServiceModule()).getInstance(UserService.class);
    }

    @GET
    @Path("/hello")
    public String returnStrings() {
        return "Hello World";
    }

    @GET
    @Path("/getUser")
    public Response getUser(@QueryParam("name") String name) {
        log.info("=============================Received param : {} =================================", name);
        if (name == null || name.equals("")) {
            return new Response().setStatus(1).setMessage("Param is null.");
        }
        User user;
        try {
            user = userService.findUserByName(name);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Response().setStatus(1).setMessage("Did not find user.");
        }
        return new Response().setStatus(0).setData(user);
    }

    @POST
    @Path("/addUser")
    public Response addUser(UserDTO userDTO) {
        if (userDTO == null) {
            return new Response().setStatus(1).setMessage("Param is null.");
        }
        try {
            userService.createUser(userDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Response().setStatus(1).setMessage("Add user failed.");
        }
        return new Response().setStatus(0).setMessage("Add user succeed.");
    }

    @DELETE
    @Path("/deleteUser")
    public Response deleteUser(@QueryParam("name") String name) {
        if (name == null) {
            return new Response().setStatus(1).setMessage("Param is null.");
        }
        try {
            userService.deleteUser(name);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Response().setStatus(1).setMessage("Delete user failed.");
        }
        return new Response().setStatus(0).setMessage("Delete user succeed.");
    }

    @PUT
    @Path("/updateUser")
    public Response updateUser(UserDTO userDTO) {
        if (userDTO == null) {
            return new Response().setStatus(1).setMessage("Param is null.");
        }
        try {
            userService.updateUser(userDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new Response().setStatus(1).setMessage("Update user failed.");
        }
        return new Response().setStatus(0).setMessage("Update user succeed.");
    }

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}