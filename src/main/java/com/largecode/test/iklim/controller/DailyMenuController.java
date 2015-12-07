package com.largecode.test.iklim.controller;

import com.largecode.test.iklim.controller.response.RESTResponse;
import com.largecode.test.iklim.entity.DailyMenu;
import com.largecode.test.iklim.exception.UserNotFoundException;
import com.largecode.test.iklim.exception.UserNotPermissionException;
import com.largecode.test.iklim.service.AdminService;
import com.largecode.test.iklim.service.CheckRoleService;
import com.largecode.test.iklim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by iklimovskiy on 30.11.2015.
 */
@RestController
@RequestMapping("/{login}/daily-menu")
public class DailyMenuController extends ExceptionHandlerController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

    @Autowired
    CheckRoleService checkRoleService;

    @RequestMapping(method = RequestMethod.POST, path = "")
    public RESTResponse post(@PathVariable("login") String login, @RequestBody DailyMenu dailyMenu) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.ADMIN);
        return RESTResponse.generateSuccess(adminService.addMenu(dailyMenu));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "")
    public RESTResponse put(@PathVariable("login") String login, @RequestBody DailyMenu dailyMenu) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.ADMIN);
        return RESTResponse.generateSuccess(adminService.addMenu(dailyMenu));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{restaurantId}")
    public RESTResponse get(@PathVariable("login") String login, @PathVariable("restaurantId") Long restaurantId) throws Exception {
        return RESTResponse.generateSuccess(userService.getDailyMenuInRestaurant(restaurantId));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{restaurantId}")
    public RESTResponse delete(@PathVariable("login") String login, @PathVariable("restaurantId") Long restaurantId) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.ADMIN);
        adminService.deleteRestaurant(restaurantId);
        return RESTResponse.generateEmptySuccess();
    }

    @RequestMapping(method = RequestMethod.GET, path = "")
    public RESTResponse getAll(@PathVariable("login") String login) {
        return RESTResponse.generateSuccess(userService.getDailyMenusByDay(new Date()));
    }
}
