package com.largecode.test.iklim.controller;

import com.largecode.test.iklim.controller.response.RESTResponse;
import com.largecode.test.iklim.entity.Restaurant;
import com.largecode.test.iklim.service.AdminService;
import com.largecode.test.iklim.service.CheckRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by iklimovskiy on 29.11.2015.
 */
@RestController
@RequestMapping("/{login}/restaurant")
public class RestaurantController extends ExceptionHandlerController {

    @Autowired
    AdminService adminService;

    @Autowired
    CheckRoleService checkRoleService;

    @RequestMapping(method = RequestMethod.POST)
    public RESTResponse post(@PathVariable("login") String login, @RequestBody Restaurant restaurant) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.ADMIN);
        return RESTResponse.generateSuccess(adminService.addRestaurant(restaurant));
    }

    @RequestMapping(method = RequestMethod.PUT)
    public RESTResponse put(@PathVariable("login") String login, @RequestBody Restaurant restaurant) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.ADMIN);
        return RESTResponse.generateSuccess(adminService.addRestaurant(restaurant));
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{restaurantId}")
    public RESTResponse get(@PathVariable("login") String login, @PathVariable("restaurantId") Long restaurantId) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.ADMIN);
        return RESTResponse.generateSuccess(adminService.getRestaurant(restaurantId));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{restaurantId}")
    public RESTResponse delete(@PathVariable("login") String login, @PathVariable("restaurantId") Long restaurantId) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.ADMIN);
        adminService.deleteRestaurant(restaurantId);
        return RESTResponse.generateEmptySuccess();
    }

    @RequestMapping(method = RequestMethod.GET)
    public RESTResponse getAll(@PathVariable("login") String login) throws Exception {
        checkRoleService.containRole(login, CheckRoleService.Role.ADMIN);
        return RESTResponse.generateSuccess(adminService.getRestaurants());
    }
}
