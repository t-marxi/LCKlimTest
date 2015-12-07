package com.largecode.test.iklim.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.largecode.test.iklim.entity.base.IdEntity;
import lombok.*;

import javax.persistence.*;

/**
 * Created by iklimovskiy on 29.11.2015.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
public class Dish extends IdEntity {

    @NonNull
    @Column(name = "dish_name")
    private String name;

    @NonNull
    @Column(name = "dish_price")
    private String price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_menu_id", nullable = false)
    @JsonIgnore
    private DailyMenu dailyMenu;

}
