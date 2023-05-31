package com.github.joaoprg.ubereats.clone.register.service;

import com.github.joaoprg.ubereats.clone.register.entity.Dish;
import com.github.joaoprg.ubereats.clone.register.entity.Restaurant;
import com.github.joaoprg.ubereats.clone.register.exception.ServiceErrorCode;
import com.github.joaoprg.ubereats.clone.register.exception.ServiceException;
import com.github.joaoprg.ubereats.clone.register.mapper.DishMapper;
import com.github.joaoprg.ubereats.clone.register.model.DishCreate;
import com.github.joaoprg.ubereats.clone.register.model.DishEmbeddedList;
import com.github.joaoprg.ubereats.clone.register.model.DishList;
import com.github.joaoprg.ubereats.clone.register.model.DishRead;
import com.github.joaoprg.ubereats.clone.register.model.DishUpdate;
import com.github.joaoprg.ubereats.clone.register.repository.DishRepository;
import com.github.joaoprg.ubereats.clone.register.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.UUID;

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
        try {
            final Restaurant restaurant = restaurantRepository.readByIdOptional(restaurantId);
            Dish dish = dishMapper.toDish(dishCreate);
            dish.restaurant = restaurant;
            restaurant.dishes.add(dish);
            dishRepository.persist(dish);
            restaurantRepository.persist(restaurant);
            return dishMapper.toDishRead(dish);
        } catch (NotFoundException e) {
            throw new ServiceException(e, ServiceErrorCode.RESTAURANT_NOT_FOUND);
        }
    }

    public DishList read(final int page, final int perPage) {
        log.debug("Reading dishes...");
        List<DishRead> dishReadList = dishRepository.findAll()
                .page(page - 1, perPage)
                .stream()
                .map(dishMapper::toDishRead)
                .toList();
        return DishList.builder()
                .total((int) dishRepository.count())
                .count(dishReadList.size())
                .page(page)
                .perPage(perPage)
                .dishEmbeddedList(new DishEmbeddedList(dishReadList))
                .build();
    }

    public DishList readByRestaurant(final UUID restaurantId, final int page, final int perPage) {
        log.debug(String.format("Reading all dishes for restaurant [Restaurant Id: %s]", restaurantId));
        try {
            restaurantRepository.readByIdOptional(restaurantId);
            List<DishRead> dishReadList = dishRepository.find("restaurantId", restaurantId)
                    .page(page - 1, perPage)
                    .stream()
                    .map(dishMapper::toDishRead)
                    .toList();
            return DishList.builder()
                    .total((int) dishRepository.count("restaurantId", restaurantId))
                    .count(dishReadList.size())
                    .page(page)
                    .perPage(perPage)
                    .dishEmbeddedList(new DishEmbeddedList(dishReadList))
                    .build();
        } catch (NotFoundException e) {
            throw new ServiceException(e, ServiceErrorCode.RESTAURANT_NOT_FOUND);
        }
    }

    @Transactional(REQUIRED)
    public DishRead update(final UUID restaurantId, final UUID dishId, final DishUpdate dishUpdate) {
        log.debug(String.format("Updating dish...[Id: %s] [Restaurant Id: %s]", dishId, restaurantId));
        try {
            final Dish dish = dishRepository.readByIdOptional(restaurantId, dishId);
            dishMapper.toDish(dishUpdate, dish);
            dishRepository.persist(dish);
            return dishMapper.toDishRead(dish);
        } catch (NotFoundException e) {
            throw new ServiceException(e, ServiceErrorCode.DISH_NOT_FOUND);
        }
    }

    @Transactional(REQUIRED)
    public void delete(final UUID restaurantId, final UUID dishId) {
        log.debug(String.format("Deleting dish...[Id: %s] [Restaurant Id: %s]", dishId, restaurantId));
        try {
            final Dish dish = dishRepository.readByIdOptional(restaurantId, dishId);
            dishRepository.delete(dish);
        } catch (NotFoundException e) {
            throw new ServiceException(e, ServiceErrorCode.RESTAURANT_NOT_FOUND);
        }
    }
}
