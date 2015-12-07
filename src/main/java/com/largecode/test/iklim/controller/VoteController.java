package com.largecode.test.iklim.controller;

import com.largecode.test.iklim.controller.response.RESTResponse;
import com.largecode.test.iklim.entity.User;
import com.largecode.test.iklim.entity.Vote;
import com.largecode.test.iklim.exception.VoteTimeOverException;
import com.largecode.test.iklim.service.CheckRoleService;
import com.largecode.test.iklim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by iklimovskiy on 29.11.2015.
 */
@RestController
@RequestMapping("/{login}/vote")
public class VoteController extends ExceptionHandlerController {

    @Autowired
    UserService userService;

    @Autowired
    CheckRoleService checkRoleService;

    @RequestMapping(method = RequestMethod.POST, path = "/{restaurantId}")
    public RESTResponse post(@PathVariable("login") String login, @PathVariable("restaurantId") Long restaurantId) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.ADMIN);
        return RESTResponse.generateSuccess(userService.vote(login, restaurantId));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public RESTResponse put(@PathVariable("login") String login, @PathVariable("restaurantId") Long restaurantId) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.USER);
        return RESTResponse.generateSuccess(userService.vote(login, restaurantId));
    }

    @RequestMapping(method = RequestMethod.GET)
    public RESTResponse get(@PathVariable("login") String login) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.ADMIN);
        return RESTResponse.generateSuccess(userService.getVote(login));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{voteId}")
    public RESTResponse delete(@PathVariable("login") String login, @PathVariable("voteId") Long voteId) {
        throw new IllegalStateException("This method is not available. If you wanted to change vote use vote 'PUT'.");
    }
}
