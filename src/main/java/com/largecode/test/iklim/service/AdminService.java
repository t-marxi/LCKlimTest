package com.largecode.test.iklim.service;

import com.largecode.test.iklim.dto.Result;
import com.largecode.test.iklim.entity.DailyMenu;
import com.largecode.test.iklim.entity.Restaurant;
import com.largecode.test.iklim.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by iklimovskiy on 29.11.2015.
 */
public interface AdminService {

    DailyMenu addMenu(DailyMenu menu);

    Restaurant addRestaurant(Restaurant restaurant);

    Restaurant getRestaurant(Long restaurantId);

    User addUser(User user);

    void deleteRestaurant(Long restaurantId);

    void deleteUser(String login);

    void deleteMenu(Long menuId);

    User getUser(String login);

    List<User> getUsers();

    void deleteDailyMenuInRestaurant(Long restaurantId);

    List<Restaurant> getRestaurants();

    List<Result> getResult();
}
