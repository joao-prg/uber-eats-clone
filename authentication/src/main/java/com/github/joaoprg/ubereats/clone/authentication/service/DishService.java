package com.github.joaoprg.ubereats.clone.authentication.service;

import com.github.joaoprg.ubereats.clone.authentication.entity.Dish;
import com.github.joaoprg.ubereats.clone.authentication.entity.Restaurant;
import com.github.joaoprg.ubereats.clone.authentication.mapper.DishMapper;
import com.github.joaoprg.ubereats.clone.authentication.model.DishCreate;
import com.github.joaoprg.ubereats.clone.authentication.model.DishRead;
import com.github.joaoprg.ubereats.clone.authentication.model.DishUpdate;
import com.github.joaoprg.ubereats.clone.authentication.repository.DishRepository;
import com.github.joaoprg.ubereats.clone.authentication.repository.RestaurantRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static javax.transaction.Transactional.TxType.REQUIRED;

@ApplicationScoped
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
    public DishRead create(final UUID restaurantId, @Valid final DishCreate dishCreate) {
        final Restaurant restaurant =
                restaurantRepository.findByIdOptional(restaurantId)
                        .orElseThrow(() -> new NotFoundException(
                                String.format("Restaurant not found! [Id: %s]", restaurantId))
                        );
        Dish dish = dishMapper.toDish(dishCreate);
        dish.restaurant = restaurant;
        dishRepository.persist(dish);
        return dishMapper.toDishRead(dish);
    }

    public List<DishRead> readAll() {
        List<Dish> dishes = dishRepository.listAll();
        return dishes.stream().map(dishMapper::toDishRead).collect(Collectors.toList());
    }

    public List<DishRead> readByRestaurant(final UUID restaurantId) {
        restaurantRepository.findByIdOptional(restaurantId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Restaurant not found! [Id: %s]", restaurantId))
                );
        List<Dish> dishes = dishRepository.readByRestaurant(restaurantId);
        return dishes.stream().map(dishMapper::toDishRead).collect(Collectors.toList());
    }

    @Transactional(REQUIRED)
    public DishRead update(final UUID restaurantId, final UUID dishId, @Valid final DishUpdate dishUpdate) {
        restaurantRepository.findByIdOptional(restaurantId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Restaurant not found! [Id: %s]", restaurantId))
                );
        final Dish dish =
                dishRepository.findByIdOptional(dishId)
                        .orElseThrow(() -> new NotFoundException(
                                String.format("Dish not found! [Id: %s]", dishId))
                        );
        dishMapper.toDish(dishUpdate, dish);
        dishRepository.persist(dish);
        return dishMapper.toDishRead(dish);
    }

    @Transactional(REQUIRED)
    public void delete(final UUID restaurantId, final UUID dishId) {
        restaurantRepository.findByIdOptional(restaurantId)
                .orElseThrow(() -> new NotFoundException(
                        String.format("Restaurant not found! [Id: %s]", restaurantId))
                );
        final Dish dish =
                dishRepository.findByIdOptional(dishId)
                        .orElseThrow(() -> new NotFoundException(
                                String.format("Dish not found! [Id: %s]", dishId))
                        );
        dishRepository.delete(dish);
    }
}
