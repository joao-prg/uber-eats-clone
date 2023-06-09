package com.github.joaoprg.ubereats.clone.register.service;

import com.github.joaoprg.ubereats.clone.register.entity.Restaurant;
import com.github.joaoprg.ubereats.clone.register.exception.ServiceErrorCode;
import com.github.joaoprg.ubereats.clone.register.exception.ServiceException;
import com.github.joaoprg.ubereats.clone.register.mapper.RestaurantMapper;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantCreate;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantEmbeddedList;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantList;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantRead;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantUpdate;
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
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    private final RestaurantOwnerValidationService restaurantOwnerValidationService;

    @Inject
    public RestaurantService(
            final RestaurantRepository restaurantRepository,
            final RestaurantMapper restaurantMapper,
            final RestaurantOwnerValidationService restaurantOwnerValidationService) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
        this.restaurantOwnerValidationService = restaurantOwnerValidationService;
    }

    @Transactional(REQUIRED)
    public RestaurantRead create(final RestaurantCreate restaurantCreate, final String username) {
        log.debug(String.format("Creating restaurant...[Name: %s]", restaurantCreate.name));
        Restaurant restaurant = restaurantMapper.toRestaurant(restaurantCreate);
        restaurant.setOwner(username);
        restaurant.getAddress().setRestaurant(restaurant);
        restaurant = restaurantRepository.create(restaurant);
        return restaurantMapper.toRestaurantRead(restaurant);
    }

    public RestaurantList read(final int page, final int perPage) {
        log.debug("Reading restaurants...");
        List<RestaurantRead> restaurantReadList = restaurantRepository.findAll()
                .page(page - 1, perPage)
                .stream()
                .map(restaurantMapper::toRestaurantRead)
                .toList();
        return RestaurantList.builder()
                .total((int) restaurantRepository.count())
                .count(restaurantReadList.size())
                .page(page)
                .perPage(perPage)
                .restaurantEmbeddedList(new RestaurantEmbeddedList(restaurantReadList))
                .build();
    }


    @Transactional(REQUIRED)
    public RestaurantRead update(final UUID restaurantId,
                                 final RestaurantUpdate restaurantUpdate,
                                 final String username) {
        log.debug(String.format("Updating restaurant...[Id: %s]", restaurantId));
        try {
            final Restaurant restaurant = restaurantRepository.readByIdOptional(restaurantId);
            restaurantOwnerValidationService.checkOwner(username, restaurant.owner, restaurantId);
            restaurantMapper.toRestaurant(restaurantUpdate, restaurant);
            restaurantRepository.persist(restaurant);
            return restaurantMapper.toRestaurantRead(restaurant);
        } catch (NotFoundException e) {
            throw new ServiceException(e, ServiceErrorCode.RESTAURANT_NOT_FOUND);
        }
    }

    @Transactional(REQUIRED)
    public void delete(final UUID restaurantId, final String username) {
        log.debug(String.format("Deleting restaurant...[Id: %s]", restaurantId));
        try {
            final Restaurant restaurant = restaurantRepository.readByIdOptional(restaurantId);
            restaurantOwnerValidationService.checkOwner(username, restaurant.owner, restaurantId);
            restaurantRepository.delete(restaurant);
        } catch (NotFoundException e) {
            throw new ServiceException(e, ServiceErrorCode.RESTAURANT_NOT_FOUND);
        }
    }
}
