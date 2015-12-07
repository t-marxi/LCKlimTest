package com.largecode.test.iklim.repository;

import com.largecode.test.iklim.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by iklimovskiy on 03.12.2015.
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

}
