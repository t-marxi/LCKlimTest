package com.largecode.test.iklim.service.impl;

import com.largecode.test.iklim.dto.Result;
import com.largecode.test.iklim.entity.DailyMenu;
import com.largecode.test.iklim.entity.Dish;
import com.largecode.test.iklim.entity.Restaurant;
import com.largecode.test.iklim.entity.User;
import com.largecode.test.iklim.repository.*;
import com.largecode.test.iklim.service.AdminService;
import com.largecode.test.iklim.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * Created by iklimovskiy on 04.12.2015.
 */
@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    DishRepository dishRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DailyMenuRepository dailyMenuRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    VoteRepository voteRepository;

    @Override
    public DailyMenu addMenu(DailyMenu dailyMenu) {
        dailyMenu.setDate(new Date());
        deleteDailyMenuInRestaurant(dailyMenu.getRestaurant().getId());
        DailyMenu newDailyMenu = dailyMenuRepository.save(dailyMenu);
        newDailyMenu.setDishes(dailyMenu.getDishes().stream().map(d -> {
            d.setDailyMenu(newDailyMenu);
            return dishRepository.save(d);
        }).collect(Collectors.toList()));
        return newDailyMenu;
    }

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant getRestaurant(Long restaurantId) {
        return restaurantRepository.findOne(restaurantId);
    }

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        restaurantRepository.delete(restaurantId);
    }

    @Override
    public void deleteUser(String login) {
        userRepository.delete(login);
    }

    @Override
    public void deleteMenu(Long menuId) {
        dailyMenuRepository.delete(menuId);
    }

    @Override
    public User getUser(String login) {
        return userRepository.findOne(login);
    }

    @Override
    public List<User> getUsers() {
        List<User> list = new LinkedList<>();
        userRepository.findAll().forEach(u -> list.add(u));
        return list;
    }

    @Override
    public void deleteDailyMenuInRestaurant(Long restaurantId) {
        Restaurant restaurant = Objects.requireNonNull(restaurantRepository.findOne(restaurantId), String.format("Restaurant was not found."));
        Date currentDay = new Date();
        List<DailyMenu> dailyMenus = dailyMenuRepository.getDailyMenuByStartAndFinishDateAndRestaurant(DateUtil.getStartOfDay(currentDay), DateUtil.getEndOfDay(currentDay), restaurant);
        if (!CollectionUtils.isEmpty(dailyMenus)) {
            dailyMenus.stream().forEach(dm -> {
                // Cascade of so need to delete manual.
                if (!CollectionUtils.isEmpty(dm.getDishes())) {
                    dm.getDishes().stream().forEach(dish -> dishRepository.delete(dish));
                }
                dailyMenuRepository.delete(dm);
            });
        }
    }

    @Override
    public List<Restaurant> getRestaurants() {
        List<Restaurant> list = new LinkedList<>();
        restaurantRepository.findAll().forEach(restaurant -> list.add(restaurant));
        return list;
    }

    @Override
    public List<Result> getResult() {
        Date currentDay = new Date();
        return voteRepository.getResultsByRestaurant(DateUtil.getStartOfDay(currentDay), DateUtil.getEndOfDay(currentDay));
    }
}
