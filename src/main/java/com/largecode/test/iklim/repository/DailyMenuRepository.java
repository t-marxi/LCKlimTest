package com.largecode.test.iklim.repository;

import com.largecode.test.iklim.entity.DailyMenu;
import com.largecode.test.iklim.entity.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by iklimovskiy on 03.12.2015.
 */
@Repository
public interface DailyMenuRepository extends PagingAndSortingRepository<DailyMenu, Long> {

    @Query("Select dm from DailyMenu dm where dm.date between ?1 and ?2")
    public List<DailyMenu> getDailyMenuByStartAndFinishDate(Date startDate, Date finishDate);

    @Query("Select dm from DailyMenu dm where dm.date between ?1 and ?2 and dm.restaurant = ?3")
    public List<DailyMenu> getDailyMenuByStartAndFinishDateAndRestaurant(Date startDate, Date finishDate, Restaurant restaurant);

}
