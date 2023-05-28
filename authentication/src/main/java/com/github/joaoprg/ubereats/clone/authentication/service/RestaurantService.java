package com.github.joaoprg.ubereats.clone.authentication.service;

import com.github.joaoprg.ubereats.clone.authentication.entity.Restaurant;
import com.github.joaoprg.ubereats.clone.authentication.exception.ServiceException;
import com.github.joaoprg.ubereats.clone.authentication.mapper.RestaurantMapper;
import com.github.joaoprg.ubereats.clone.authentication.model.RestaurantCreate;
import com.github.joaoprg.ubereats.clone.authentication.model.RestaurantEmbeddedList;
import com.github.joaoprg.ubereats.clone.authentication.model.RestaurantList;
import com.github.joaoprg.ubereats.clone.authentication.model.RestaurantRead;
import com.github.joaoprg.ubereats.clone.authentication.model.RestaurantUpdate;
import com.github.joaoprg.ubereats.clone.authentication.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.UUID;

import static javax.transaction.Transactional.TxType.REQUIRED;

@ApplicationScoped
@Slf4j
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    @Inject
    public RestaurantService(
            final RestaurantRepository restaurantRepository,
            final RestaurantMapper restaurantMapper) {
        this.restaurantRepository = restaurantRepository;
        this.restaurantMapper = restaurantMapper;
    }

    @Transactional(REQUIRED)
    public RestaurantRead create(final RestaurantCreate restaurantCreate) {
        log.debug(String.format("Creating restaurant...[Name: %s]", restaurantCreate.name));
        try {
            Restaurant restaurant = restaurantMapper.toRestaurant(restaurantCreate);
            restaurant.getAddress().setRestaurant(restaurant);
            restaurant = restaurantRepository.create(restaurant);
            return restaurantMapper.toRestaurantRead(restaurant);
        } catch (Exception e) {
            if (e instanceof NotFoundException) {
                throw new ServiceException(Response.Status.NOT_FOUND.getStatusCode(), e);
            }
            throw new ServiceException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e);
        }
    }

    public RestaurantList read(final int page, final int perPage) {
        log.debug("Reading restaurants...");
        try {
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
        } catch (Exception e) {
            throw new ServiceException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e);
        }
    }


    @Transactional(REQUIRED)
    public RestaurantRead update(final UUID restaurantId, final RestaurantUpdate restaurantUpdate) {
        log.debug(String.format("Updating restaurant...[Id: %s]", restaurantId));
        try {
            final Restaurant restaurant = restaurantRepository.readByIdOptional(restaurantId);
            restaurantMapper.toRestaurant(restaurantUpdate, restaurant);
            restaurantRepository.persist(restaurant);
            return restaurantMapper.toRestaurantRead(restaurant);
        } catch (Exception e) {
            if (e instanceof NotFoundException) {
                throw new ServiceException(Response.Status.NOT_FOUND.getStatusCode(), e);
            }
            throw new ServiceException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e);
        }
    }

    @Transactional(REQUIRED)
    public void delete(final UUID restaurantId) {
        log.debug(String.format("Deleting restaurant...[Id: %s]", restaurantId));
        try {
            final Restaurant restaurant = restaurantRepository.readByIdOptional(restaurantId);
            restaurantRepository.delete(restaurant);
        } catch (Exception e) {
            if (e instanceof NotFoundException) {
                throw new ServiceException(Response.Status.NOT_FOUND.getStatusCode(), e);
            }
            throw new ServiceException(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e);
        }
    }
}
