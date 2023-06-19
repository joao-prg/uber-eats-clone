package com.github.joaoprg.ubereats.clone.register.repository;

import com.github.joaoprg.ubereats.clone.register.entity.Dish;
import com.github.joaoprg.ubereats.clone.register.entity.DishKey;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class DishRepository implements PanacheRepositoryBase<Dish, DishKey> {
    private static final String JPA_QUERY_BY_RESTAURANT = "restaurant_id = :restaurantId";

    public List<Dish> readByRestaurant(final UUID restaurantId) {
        return find(JPA_QUERY_BY_RESTAURANT, restaurantId).list();
    }

    public Dish readByIdOptional(final UUID restaurantId, final UUID dishId) {
        return this.findByIdOptional(new DishKey(restaurantId, dishId))
                .orElseThrow(() -> new NotFoundException(
                        String.format("Dish not found! [Id: %s] [Restaurant Id: %s]", dishId, restaurantId))
                );
    }
}
