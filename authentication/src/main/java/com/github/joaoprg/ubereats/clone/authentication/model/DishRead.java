package com.github.joaoprg.ubereats.clone.authentication.model;

import java.math.BigDecimal;

public class DishRead {

    public Long id;

    public String name;

    public String description;

    public RestaurantRead restaurant;

    public BigDecimal price;
}