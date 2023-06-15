package com.github.joaoprg.ubereats.clone.register.resource;

import com.github.joaoprg.ubereats.clone.register.model.DishCreate;
import com.github.joaoprg.ubereats.clone.register.model.DishRead;
import com.github.joaoprg.ubereats.clone.register.model.DishUpdate;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantCreate;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantRead;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantUpdate;
import com.github.joaoprg.ubereats.clone.register.service.DishService;
import com.github.joaoprg.ubereats.clone.register.service.RestaurantService;
import io.quarkus.security.identity.SecurityIdentity;

import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.UUID;

public class RestaurantResource implements RestaurantApi {

    private final RestaurantService restaurantService;
    private final DishService dishService;
    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    public RestaurantResource(
            final RestaurantService restaurantService,
            final DishService dishService) {
        this.restaurantService = restaurantService;
        this.dishService = dishService;
    }


    @Override
    public Response createRestaurant(RestaurantCreate restaurantCreate, @Context UriInfo uriInfo) {
        final RestaurantRead restaurantRead = restaurantService.create(restaurantCreate,
                securityIdentity.getPrincipal().getName());
        final URI uri = uriInfo.getAbsolutePathBuilder().path(restaurantRead.getId().toString()).build();
        return Response.created(uri).entity(restaurantRead).build();
    }

    @Override
    public Response readRestaurants(
            @QueryParam("page") final Integer page,
            @QueryParam("per_page") final Integer perPage,
            @Context UriInfo uriInfo) {
        return Response.ok(restaurantService.read(page, perPage)).build();
    }

    @Override
    public Response updateRestaurant(@PathParam("restaurant_id") UUID restaurantId,
                                     RestaurantUpdate restaurantUpdate,
                                     @Context UriInfo uriInfo) {
        final RestaurantRead restaurantRead = restaurantService.update(restaurantId,
                restaurantUpdate, securityIdentity.getPrincipal().getName());
        return Response.ok(uriInfo.getAbsolutePath()).entity(restaurantRead).build();
    }

    @Override
    public Response deleteRestaurant(@PathParam("restaurant_id") UUID restaurantId,
                                     @Context UriInfo uriInfo) {
        restaurantService.delete(restaurantId, securityIdentity.getPrincipal().getName());
        return Response.noContent().build();
    }

    @Override
    public Response createDish(@PathParam("restaurant_id") UUID restaurantId,
                               DishCreate dishCreate,
                               @Context UriInfo uriInfo) {
        final DishRead dishRead = dishService.create(restaurantId, dishCreate,
                securityIdentity.getPrincipal().getName());
        final URI uri = uriInfo.getAbsolutePathBuilder().path(dishRead.getId().toString()).build();
        return Response.created(uri).entity(dishRead).build();
    }

    @Override
    public Response readDishes(
            @QueryParam("page") final Integer page,
            @QueryParam("per_page") final Integer perPage,
            @Context UriInfo uriInfo) {
        return Response.ok(dishService.read(page, perPage)).build();
    }

    @Override
    public Response readDishesByRestaurant(
            @PathParam("restaurant_id") UUID restaurantId,
            @QueryParam("page") final Integer page,
            @QueryParam("per_page") final Integer perPage,
            @Context UriInfo uriInfo) {
        return Response.ok(dishService.readByRestaurant(restaurantId, page, perPage)).build();
    }

    @Override
    public Response updateDish(@PathParam("restaurant_id") UUID restaurantId,
                               @PathParam("dish_id") UUID dishId,
                               DishUpdate dishUpdate,
                               @Context UriInfo uriInfo) {
        final DishRead dishRead = dishService.update(restaurantId, dishId, dishUpdate,
                securityIdentity.getPrincipal().getName());
        return Response.ok(uriInfo.getAbsolutePath()).entity(dishRead).build();
    }

    @Override
    public Response deleteDish(@PathParam("restaurant_id") UUID restaurantId,
                               @PathParam("dish_id") UUID dishId,
                               @Context UriInfo uriInfo) {
        dishService.delete(restaurantId, dishId, securityIdentity.getPrincipal().getName());
        return Response.noContent().build();
    }
}
