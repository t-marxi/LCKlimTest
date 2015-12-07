package com.largecode.test.iklim.service.impl;

import com.largecode.test.iklim.entity.DailyMenu;
import com.largecode.test.iklim.entity.Restaurant;
import com.largecode.test.iklim.entity.User;
import com.largecode.test.iklim.entity.Vote;
import com.largecode.test.iklim.exception.VoteTimeOverException;
import com.largecode.test.iklim.repository.DailyMenuRepository;
import com.largecode.test.iklim.repository.RestaurantRepository;
import com.largecode.test.iklim.repository.UserRepository;
import com.largecode.test.iklim.repository.VoteRepository;
import com.largecode.test.iklim.service.UserService;
import com.largecode.test.iklim.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by iklimovskiy on 04.12.2015.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Value("${timeover.hour:11}")
    Integer timeoverHour;

    @Autowired
    DailyMenuRepository dailyMenuRepository;

    @Autowired
    VoteRepository voteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public List<DailyMenu> getDailyMenusByDay(Date date) {
        Date currentDay = new Date();
        return dailyMenuRepository.getDailyMenuByStartAndFinishDate(DateUtil.getStartOfDay(currentDay), DateUtil.getEndOfDay(currentDay));
    }

    @Override
    public Vote vote(String login, Long restaurantId) throws VoteTimeOverException {
        Date currentDate = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(currentDate);
        if (calendar.get(Calendar.HOUR_OF_DAY) > timeoverHour) {
            throw new VoteTimeOverException();
        }
        User user = Objects.requireNonNull(userRepository.findOne(login), String.format("User %s was not found.", login));
        Restaurant restaurant = Objects.requireNonNull(restaurantRepository.findOne(restaurantId), String.format("Restaurant was not found."));
        return voteRepository.save(new Vote(user, restaurant, new Date()));
    }

    @Override
    public DailyMenu getDailyMenuInRestaurant(Long restaurantId) {
        Restaurant restaurant = Objects.requireNonNull(restaurantRepository.findOne(restaurantId), String.format("Restaurant was not found."));
        Date currentDay = new Date();
        List<DailyMenu> dailyMenus =
                dailyMenuRepository.getDailyMenuByStartAndFinishDateAndRestaurant(DateUtil.getStartOfDay(currentDay), DateUtil.getEndOfDay(currentDay), restaurant);
        if (CollectionUtils.isEmpty(dailyMenus)) {
            throw new NullPointerException("There is no daily menu for this restaurant.");
        } else if (dailyMenus.size() > 1) {
            throw new IllegalStateException("Data is not valid in DB.");
        }
        return dailyMenus.get(0);
    }

    @Override
    public Vote getVote(String login) {
        User user = Objects.requireNonNull(userRepository.findOne(login), String.format("User %s was not found.", login));
        Date currentDay = new Date();
        return voteRepository.getVoteByStartAndFinishDateAndUser(DateUtil.getStartOfDay(currentDay), DateUtil.getEndOfDay(currentDay), user);
    }
}
