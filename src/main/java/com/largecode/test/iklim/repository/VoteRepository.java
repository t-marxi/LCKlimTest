package com.largecode.test.iklim.repository;

import com.largecode.test.iklim.dto.Result;
import com.largecode.test.iklim.entity.DailyMenu;
import com.largecode.test.iklim.entity.Restaurant;
import com.largecode.test.iklim.entity.User;
import com.largecode.test.iklim.entity.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by iklimovskiy on 04.12.2015.
 */
public interface VoteRepository extends PagingAndSortingRepository<Vote, String> {

    @Query("Select v from Vote v where v.date between ?1 and ?2 and v.user = ?3")
    Vote getVoteByStartAndFinishDateAndUser(Date startDate, Date finishDate, User user);

    @Query("Select v.restaurant, Count(v.id) from Vote v where v.date between ?1 and ?2 group by v.restaurant")
    List<Result> getResultsByRestaurant(Date startOfDay, Date endOfDay);
}
