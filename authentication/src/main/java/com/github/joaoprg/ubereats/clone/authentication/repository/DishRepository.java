package com.github.joaoprg.ubereats.clone.authentication.repository;

import com.github.joaoprg.ubereats.clone.authentication.entity.Dish;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class DishRepository implements PanacheRepositoryBase<Dish, UUID> {
    private static final String JPA_QUERY_BY_RESTAURANT = "restaurant_id = :restaurantId";

    public List<Dish> readByRestaurant(final UUID restaurantId) {
        return find(JPA_QUERY_BY_RESTAURANT, restaurantId).list();
    }
}
