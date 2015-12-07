package com.largecode.test.iklim.stub;

import com.largecode.test.iklim.entity.DailyMenu;
import com.largecode.test.iklim.entity.Dish;
import com.largecode.test.iklim.entity.Restaurant;
import com.largecode.test.iklim.entity.User;
import com.largecode.test.iklim.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;

/**
 * Created by iklimovskiy on 04.12.2015.
 */
@Component
public class LoadDataStub {

    @Autowired
    AdminService adminService;

    @PostConstruct
    public void init() {
        // add Admin
        adminService.addUser(new User("admin", true, "Admin"));
        //add User
        adminService.addUser(new User("user", false, "User"));
        //add restaurant "Michelin"
        Restaurant restaurant = adminService.addRestaurant(new Restaurant("Michelin"));
        //add Menu
        adminService.addMenu(new DailyMenu(new Date(), restaurant, Collections.singletonList(new Dish("stake", "100"))));
    }
}
