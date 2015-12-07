package com.largecode.test.iklim.entity;

import com.largecode.test.iklim.entity.base.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by iklimovskiy on 29.11.2015.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vote extends IdEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;

    @Column(name = "vote_date")
    private Date date;
}
