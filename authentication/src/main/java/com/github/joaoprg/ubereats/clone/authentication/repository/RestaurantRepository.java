package com.github.joaoprg.ubereats.clone.authentication.repository;

import com.github.joaoprg.ubereats.clone.authentication.entity.Restaurant;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.UUID;

@ApplicationScoped
@Transactional
public class RestaurantRepository implements PanacheRepositoryBase<Restaurant, UUID> {

    public Restaurant create(final Restaurant restaurant) {
        persist(restaurant);
        getEntityManager().flush();
        return restaurant;
    }

    public Restaurant readByIdOptional(final UUID restaurantId) {
        return this.findByIdOptional(restaurantId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Restaurant not found! [Id: %s]", restaurantId))
                );
    }
}
