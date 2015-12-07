package com.largecode.test.iklim.repository;

import com.largecode.test.iklim.entity.Restaurant;
import com.largecode.test.iklim.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by iklimovskiy on 03.12.2015.
 */
@Repository
public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {

}
