package com.github.joaoprg.ubereats.clone.authentication.service;

import com.github.joaoprg.ubereats.clone.authentication.entity.Dish;
import com.github.joaoprg.ubereats.clone.authentication.entity.Restaurant;
import com.github.joaoprg.ubereats.clone.authentication.mapper.DishMapper;
import com.github.joaoprg.ubereats.clone.authentication.model.DishCreate;
import com.github.joaoprg.ubereats.clone.authentication.model.DishRead;
import com.github.joaoprg.ubereats.clone.authentication.model.DishUpdate;
import com.github.joaoprg.ubereats.clone.authentication.repository.DishRepository;
import com.github.joaoprg.ubereats.clone.authentication.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static javax.transaction.Transactional.TxType.REQUIRED;

@ApplicationScoped
@Slf4j
public class DishService {

    private final RestaurantRepository restaurantRepository;

    private final DishRepository dishRepository;
    private final DishMapper dishMapper;

    @Inject
    public DishService(
            final RestaurantRepository restaurantRepository,
            final DishRepository dishRepository,
            final DishMapper dishMapper) {
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
        this.dishMapper = dishMapper;
    }

    @Transactional(REQUIRED)
    public DishRead create(final UUID restaurantId, final DishCreate dishCreate) {
        log.debug(String.format("Creating dish...[Name: %s] [Restaurant Id: %s]", dishCreate.name, restaurantId));
        final Restaurant restaurant = restaurantRepository.readByIdOptional(restaurantId);
        Dish dish = dishMapper.toDish(dishCreate);
        dish.restaurant = restaurant;
        restaurant.dishes.add(dish);
        dishRepository.persist(dish);
        restaurantRepository.persist(restaurant);
        return dishMapper.toDishRead(dish);
    }

    public List<DishRead> readAll() {
        log.debug("Reading all dishes...");
        List<Dish> dishes = dishRepository.listAll();
        return dishes.stream().map(dishMapper::toDishRead).collect(Collectors.toList());
    }

    public List<DishRead> readByRestaurant(final UUID restaurantId) {
        log.debug(String.format("Reading all dishes for restaurant [Restaurant Id: %s]", restaurantId));
        restaurantRepository.readByIdOptional(restaurantId);
        List<Dish> dishes = dishRepository.readByRestaurant(restaurantId);
        return dishes.stream().map(dishMapper::toDishRead).collect(Collectors.toList());
    }

    @Transactional(REQUIRED)
    public DishRead update(final UUID restaurantId, final UUID dishId, final DishUpdate dishUpdate) {
        log.debug(String.format("Updating dish...[Id: %s] [Restaurant Id: %s]", dishId, restaurantId));
        final Dish dish = dishRepository.readByIdOptional(restaurantId, dishId);
        dishMapper.toDish(dishUpdate, dish);
        dishRepository.persist(dish);
        return dishMapper.toDishRead(dish);
    }

    @Transactional(REQUIRED)
    public void delete(final UUID restaurantId, final UUID dishId) {
        log.debug(String.format("Deleting dish...[Id: %s] [Restaurant Id: %s]", dishId, restaurantId));
        final Dish dish = dishRepository.readByIdOptional(restaurantId, dishId);
        dishRepository.delete(dish);
    }
}
