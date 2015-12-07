package com.largecode.test.iklim.service;

import com.largecode.test.iklim.entity.DailyMenu;
import com.largecode.test.iklim.entity.Vote;
import com.largecode.test.iklim.exception.VoteTimeOverException;

import java.util.Date;
import java.util.List;

/**
 * Created by iklimovskiy on 04.12.2015.
 */
public interface UserService {

    List<DailyMenu> getDailyMenusByDay(Date date);

    Vote vote(String login, Long restaurantId) throws VoteTimeOverException;

    DailyMenu getDailyMenuInRestaurant(Long restaurantId);

    Vote getVote(String login);
}
