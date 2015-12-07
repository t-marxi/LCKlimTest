package com.largecode.test.iklim.controller;

import com.largecode.test.iklim.controller.response.RESTResponse;
import com.largecode.test.iklim.exception.VoteTimeOverException;
import com.largecode.test.iklim.service.AdminService;
import com.largecode.test.iklim.service.CheckRoleService;
import com.largecode.test.iklim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by iklimovskiy on 29.11.2015.
 */
@RestController
@RequestMapping("/{login}/result")
public class ResultController extends ExceptionHandlerController {

    @Autowired
    AdminService adminService;

    @Autowired
    CheckRoleService checkRoleService;

    @RequestMapping(method = RequestMethod.GET)
    public RESTResponse get(@PathVariable("login") String login) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.ADMIN);
        return RESTResponse.generateSuccess(adminService.getResult());
    }

}
