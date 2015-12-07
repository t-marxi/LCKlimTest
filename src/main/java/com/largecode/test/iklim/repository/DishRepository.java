package com.largecode.test.iklim.repository;

import com.largecode.test.iklim.entity.Dish;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by iklimovskiy on 03.12.2015.
 */
@Repository
public interface DishRepository extends PagingAndSortingRepository<Dish, Long> {

}
