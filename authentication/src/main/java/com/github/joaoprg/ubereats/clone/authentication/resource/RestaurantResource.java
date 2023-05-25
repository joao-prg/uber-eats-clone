package com.github.joaoprg.ubereats.clone.authentication.resource;

import com.github.joaoprg.ubereats.clone.authentication.model.DishCreate;
import com.github.joaoprg.ubereats.clone.authentication.model.DishRead;
import com.github.joaoprg.ubereats.clone.authentication.model.DishUpdate;
import com.github.joaoprg.ubereats.clone.authentication.model.RestaurantCreate;
import com.github.joaoprg.ubereats.clone.authentication.model.RestaurantRead;
import com.github.joaoprg.ubereats.clone.authentication.model.RestaurantUpdate;
import com.github.joaoprg.ubereats.clone.authentication.service.DishService;
import com.github.joaoprg.ubereats.clone.authentication.service.RestaurantService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.UUID;

public class RestaurantResource implements RestaurantApi{

    private final RestaurantService restaurantService;

    private final DishService dishService;

    @Inject
    public RestaurantResource(
            final RestaurantService restaurantService,
            final DishService dishService) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;
    }


    @Override
    public Response createRestaurant(@NotNull @Valid RestaurantCreate restaurantCreate,
                                     @Context UriInfo uriInfo) {
        try {
            final RestaurantRead restaurantRead = restaurantService.create(restaurantCreate);
            final URI uri = uriInfo.getAbsolutePathBuilder().path(restaurantRead.getId().toString()).build();
            return Response.created(uri).entity(restaurantRead).build();
        } catch (Exception exc) {
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

    @Override
    public Response readAllRestaurants(@Context UriInfo uriInfo) {
        try {
            return Response.ok(restaurantService.readAll()).build();
        } catch (Exception exc) {
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

    @Override
    public Response updateRestaurant(@PathParam("restaurant_id") UUID restaurantId,
                                     @NotNull @Valid RestaurantUpdate restaurantUpdate,
                                     @Context UriInfo uriInfo) {
        try {
            final RestaurantRead restaurantRead = restaurantService.update(restaurantId, restaurantUpdate);
            return Response.ok(uriInfo.getAbsolutePath()).entity(restaurantRead).build();
        } catch (NotFoundException nfexc) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception exc) {
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

    @Override
    public Response deleteRestaurant(@PathParam("restaurant_id") UUID restaurantId,
                                     @Context UriInfo uriInfo) {
        try {
            restaurantService.delete(restaurantId);
            return Response.noContent().build();
        } catch (NotFoundException nfexc) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Override
    public Response createDish(@PathParam("restaurant_id") UUID restaurantId,
                               @NotNull @Valid DishCreate dishCreate,
                               @Context UriInfo uriInfo) {
        try {
            final DishRead dishRead = dishService.create(restaurantId, dishCreate);
            final URI uri = uriInfo.getAbsolutePathBuilder().path(dishRead.getId().toString()).build();
            return Response.created(uri).entity(dishRead).build();
        } catch (NotFoundException nfexc) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception exc) {
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

    @Override
    public Response readAllDishes(@Context UriInfo uriInfo) {
        try {
            return Response.ok(dishService.readAll()).build();
        } catch (Exception exc) {
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

    @Override
    public Response readDishesByRestaurant(@PathParam("restaurant_id") UUID restaurantId,
                                           @Context UriInfo uriInfo) {
        try {
            final List<DishRead> dishes = dishService.readByRestaurant(restaurantId);
            return Response.ok(dishes).build();
        } catch (NotFoundException nfexc) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception exc) {
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

    @Override
    public Response updateDish(@PathParam("restaurant_id") UUID restaurantId,
                               @PathParam("dish_id") UUID dishId,
                               @NotNull @Valid DishUpdate dishUpdate,
                               @Context UriInfo uriInfo) {
        try {
            final DishRead dishRead = dishService.update(restaurantId, dishId, dishUpdate);
            return Response.ok(uriInfo.getAbsolutePath()).entity(dishRead).build();
        } catch (NotFoundException nfexc) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (Exception exc) {
            return Response.serverError().entity(exc.getMessage()).build();
        }
    }

    @Override
    public Response deleteDish(@PathParam("restaurant_id") UUID restaurantId,
                               @PathParam("dish_id") UUID dishId,
                               @Context UriInfo uriInfo) {
        try {
            dishService.delete(restaurantId, dishId);
            return Response.noContent().build();
        } catch (NotFoundException nfexc) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
