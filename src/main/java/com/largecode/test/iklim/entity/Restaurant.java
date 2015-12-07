package com.largecode.test.iklim.entity;

import com.largecode.test.iklim.entity.base.IdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.util.List;

/**
 * Created by iklimovskiy on 29.11.2015.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Restaurant extends IdEntity {

    @Column(name = "restaurant_name")
    String name;
}
