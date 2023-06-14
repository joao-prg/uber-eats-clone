package com.github.joaoprg.ubereats.clone.register.service;

import javax.inject.Singleton;
import javax.ws.rs.ForbiddenException;
import java.util.UUID;

@Singleton
public class RestaurantOwnerValidationService {

    public void checkOwner(final String username, final String restaurantOwner, final UUID restaurantId) {
        if (!username.equals(restaurantOwner)) {
            throw new ForbiddenException(
                    String.format("%s is not the owner of restaurant with Id %s.", username, restaurantId));
        }
    }
}
