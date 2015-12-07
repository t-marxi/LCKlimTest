package com.largecode.test.iklim.dto;

import com.largecode.test.iklim.entity.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by iklimovskiy on 04.12.2015.
 */
@Data
@AllArgsConstructor
public class Result {

    Restaurant restaurant;

    Integer votes;
}
