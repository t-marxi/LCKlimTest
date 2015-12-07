package com.largecode.test.iklim.entity.base;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by iklimovskiy on 29.11.2015.
 */
@Data
@MappedSuperclass
public class IdEntity {
    @Id
    @GeneratedValue
    @Column(name = "id")
    protected Long id;
}
