package com.largecode.test.iklim.entity;

import com.largecode.test.iklim.entity.base.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by iklimovskiy on 29.11.2015.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DailyMenu extends IdEntity {
    @Column(name = "date")
    private Date date;

    @OneToOne
    private Restaurant restaurant;

    @OneToMany(mappedBy = "dailyMenu")
    List<Dish> dishes;
}
