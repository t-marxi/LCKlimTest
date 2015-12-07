package com.largecode.test.iklim.controller;

import com.largecode.test.iklim.controller.response.RESTResponse;
import com.largecode.test.iklim.entity.User;
import com.largecode.test.iklim.service.AdminService;
import com.largecode.test.iklim.service.CheckRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by iklimovskiy on 02.12.2015.
 */
@RestController
@RequestMapping("/{login}/user")
public class UserController extends ExceptionHandlerController {

    @Autowired
    AdminService adminService;

    @Autowired
    CheckRoleService checkRoleService;

    @RequestMapping(method = RequestMethod.POST)
    public RESTResponse post(@PathVariable("login") String login, @RequestBody User user) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.ADMIN);
        User newUser = adminService.addUser(user);
        return RESTResponse.generateSuccess(newUser);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public RESTResponse put(@PathVariable("login") String login, @RequestBody User user) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.ADMIN);
        User newUser = adminService.addUser(user);
        return RESTResponse.generateSuccess(newUser);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{userId}")
    public RESTResponse get(@PathVariable("login") String login, @PathVariable("userlogin") String userlogin) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.ADMIN);
        return RESTResponse.generateSuccess(adminService.getUser(userlogin));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{userlogin}")
    public RESTResponse delete(@PathVariable("login") String login, @PathVariable("userlogin") String userlogin) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.ADMIN);
        adminService.deleteUser(userlogin);
        return RESTResponse.generateEmptySuccess();
    }

    @RequestMapping(method = RequestMethod.GET)
    public RESTResponse getAll(@PathVariable("login") String login) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.ADMIN);
        return RESTResponse.generateSuccess(adminService.getUsers());
    }

}
