package com.github.joaoprg.ubereats.clone.authentication.resource;

import com.github.joaoprg.ubereats.clone.authentication.model.DishCreate;
import com.github.joaoprg.ubereats.clone.authentication.model.DishUpdate;
import com.github.joaoprg.ubereats.clone.authentication.model.RestaurantCreate;
import com.github.joaoprg.ubereats.clone.authentication.model.RestaurantUpdate;
import io.smallrye.common.constraint.NotNull;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/restaurants")
@Tag(ref = "Restaurants API")
public interface RestaurantApi {
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/")
    @Operation(
            operationId = "CreateRestaurant",
            summary = "Create a new restaurant."
    )
    @RequestBody(
            name = "restaurantCreate",
            description = "The restaurant to create.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(ref = "restaurant_create")
            ),
            required = true
    )
    @APIResponses(value = {
            @APIResponse(
                    name = "restaurantRead",
                    responseCode = "201",
                    description = "Information on the restaurant that was created.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "knowledge_base_read"))
            ),
            @APIResponse(
                    name = "badRequest",
                    responseCode = "400",
                    description = "Bad Request.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                    name = "unauthorized",
                    responseCode = "401",
                    description = "Unauthorized access - invalid or unverifiable JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            )
    })
    Response createRestaurant(
            @NotNull
            @Valid
            final RestaurantCreate restaurantCreate);

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "ReadAllRestaurants",
            summary = "Get a list of all restaurants."
    )
    @APIResponses(value = {
            @APIResponse(
                    name = "restaurantList",
                    responseCode = "200",
                    description = "List of restaurants found.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "restaurants_list"))
            ),
            @APIResponse(
                    name = "badRequest",
                    responseCode = "400",
                    description = "Bad Request.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                    name = "unauthorized",
                    responseCode = "401",
                    description = "Unauthorized access - invalid or unverifiable JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            )
    })
    Response readAllRestaurants();

    @PUT
    @Path("/{restaurant_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "UpdateRestaurant",
            summary = "Update a restaurant."
    )
    @RequestBody(
            name = "RestaurantUpdate",
            description = "Data to update a restaurant with.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(ref = "restaurant_update")
            ),
            required = true
    )
    @APIResponses(value = {
            @APIResponse(
                    name = "restaurantRead",
                    responseCode = "200",
                    description = "Information on the restaurant that was updated.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "restaurant_read"))
            ),
            @APIResponse(
                    name = "badRequest",
                    responseCode = "400",
                    description = "Bad Request.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            ),
            @APIResponse(
                    name = "unauthorized",
                    responseCode = "401",
                    description = "Unauthorized access - invalid or unverifiable JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            ),
            @APIResponse(
                    name = "notFound",
                    responseCode = "404",
                    description = "Restaurant Not Found.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            )
    })
    Response updateRestaurant(
            @Parameter(
                    name = "restaurant_id",
                    description = "The restaurant id.",
                    required = true,
                    example = "24e0bbea-2f5a-4061-a32b-2b2ad2ba3b22",
                    in = ParameterIn.PATH,
                    schema = @Schema(description = "uuid", type = SchemaType.STRING)
            )
            @PathParam("restaurant_id")
            final UUID restaurantId,
            @NotNull
            @Valid
            final RestaurantUpdate restaurantUpdate);

    @DELETE
    @Path("/{restaurant_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Delete a restaurant."
    )
    @APIResponses(value = {
            @APIResponse(
                    description = "Restaurant successfully deleted.",
                    responseCode = "204"
            ),
            @APIResponse(
                    name = "unauthorized",
                    responseCode = "401",
                    description = "Unauthorized access - invalid or unverifiable JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            ),
            @APIResponse(
                    name = "notFound",
                    responseCode = "404",
                    description = "Restaurant Not Found.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            )
    })
    Response deleteRestaurant(
            @Parameter(
            name = "restaurant_id",
            description = "The restaurant id.",
            required = true,
            example = "4ebfaa19-c46c-4648-a848-66f909ad6d3c",
            in = ParameterIn.PATH,
            schema = @Schema(description = "uuid", type = SchemaType.STRING)
            )
            @PathParam("restaurant_id")
            final UUID restaurantId);


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{restaurant_id}/dishes")
    @Operation(
            operationId = "CreateDish",
            summary = "Create a new dish."
    )
    @RequestBody(
            name = "dishCreate",
            description = "The dish to create.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(ref = "dish_create")
            ),
            required = true
    )
    @APIResponses(value = {
            @APIResponse(
                    name = "dishRead",
                    responseCode = "201",
                    description = "Information on the dish that was created.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "dish_read"))
            ),
            @APIResponse(
                    name = "badRequest",
                    responseCode = "400",
                    description = "Bad Request.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                    name = "unauthorized",
                    responseCode = "401",
                    description = "Unauthorized access - invalid or unverifiable JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            )
    })
    Response createDish(
            @Parameter(
                    name = "restaurant_id",
                    description = "The restaurant id.",
                    required = true,
                    example = "24e0bbea-2f5a-4061-a32b-2b2ad2ba3b22",
                    in = ParameterIn.PATH,
                    schema = @Schema(description = "uuid", type = SchemaType.STRING)
            )
            @PathParam("restaurant_id")
            final UUID restaurantId,
            @NotNull
            @Valid
            final DishCreate dishCreate);

    @GET
    @Path("/dishes")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "ReadAllDishes",
            summary = "Get a list of all dishes."
    )
    @APIResponses(value = {
            @APIResponse(
                    name = "dishList",
                    responseCode = "200",
                    description = "List of dishes found.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "dishes_list"))
            ),
            @APIResponse(
                    name = "badRequest",
                    responseCode = "400",
                    description = "Bad Request.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                    name = "unauthorized",
                    responseCode = "401",
                    description = "Unauthorized access - invalid or unverifiable JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            )
    })
    Response readAllDishes();


    @GET
    @Path("/{restaurant_id}/dishes")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "ReadDishesByRestaurant",
            summary = "Get a list of all dishes of a restaurant."
    )
    @APIResponses(value = {
            @APIResponse(
                    name = "dishListByRestaurant",
                    responseCode = "200",
                    description = "List of dishes found for a given restaurant.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "dishes_list"))
            ),
            @APIResponse(
                    name = "badRequest",
                    responseCode = "400",
                    description = "Bad Request.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                    name = "unauthorized",
                    responseCode = "401",
                    description = "Unauthorized access - invalid or unverifiable JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error"))
            )
    })
    Response readDishesByRestaurant(
            @Parameter(
                    name = "restaurant_id",
                    description = "The restaurant id.",
                    required = true,
                    example = "24e0bbea-2f5a-4061-a32b-2b2ad2ba3b22",
                    in = ParameterIn.PATH,
                    schema = @Schema(description = "uuid", type = SchemaType.STRING)
            )
            @PathParam("restaurant_id")
            final UUID restaurantId);


    @PUT
    @Path("/{restaurant_id}/dishes/{dish_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            operationId = "UpdateDish",
            summary = "Update a dish."
    )
    @RequestBody(
            name = "DishUpdate",
            description = "Data to update a dish with.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(ref = "dish_update")
            ),
            required = true
    )
    @APIResponses(value = {
            @APIResponse(
                    name = "dishRead",
                    responseCode = "200",
                    description = "Information on the dish that was updated.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "dish_read"))
            ),
            @APIResponse(
                    name = "badRequest",
                    responseCode = "400",
                    description = "Bad Request.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            ),
            @APIResponse(
                    name = "unauthorized",
                    responseCode = "401",
                    description = "Unauthorized access - invalid or unverifiable JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            ),
            @APIResponse(
                    name = "notFound",
                    responseCode = "404",
                    description = "Dish Not Found.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            )
    })
    Response updateDish(
            @Parameter(
                    name = "restaurant_id",
                    description = "The restaurant id.",
                    required = true,
                    example = "24e0bbea-2f5a-4061-a32b-2b2ad2ba3b22",
                    in = ParameterIn.PATH,
                    schema = @Schema(description = "uuid", type = SchemaType.STRING)
            )
            @PathParam("restaurant_id")
            final UUID restaurantId,
            @Parameter(
                    name = "dish_id",
                    description = "The dish id.",
                    required = true,
                    example = "7089a5b2-f7a2-4629-bffe-a5fd88dcb2e2",
                    in = ParameterIn.PATH,
                    schema = @Schema(description = "uuid", type = SchemaType.STRING)
            )
            @PathParam("dish_id")
            final UUID dishId,
            @NotNull
            @Valid
            final DishUpdate dishUpdate);

    @DELETE
    @Path("/{restaurant_id}/dishes/{dish_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            summary = "Delete a dish."
    )
    @APIResponses(value = {
            @APIResponse(
                    description = "Dish successfully deleted.",
                    responseCode = "204"
            ),
            @APIResponse(
                    name = "unauthorized",
                    responseCode = "401",
                    description = "Unauthorized access - invalid or unverifiable JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            ),
            @APIResponse(
                    name = "notFound",
                    responseCode = "404",
                    description = "Dish Not Found.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal Server Error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(ref = "error")
                    )
            )
    })
    Response deleteDish(
            @Parameter(
                    name = "restaurant_id",
                    description = "The restaurant id.",
                    required = true,
                    example = "24e0bbea-2f5a-4061-a32b-2b2ad2ba3b22",
                    in = ParameterIn.PATH,
                    schema = @Schema(description = "uuid", type = SchemaType.STRING)
            )
            @PathParam("restaurant_id")
            final UUID restaurantId,
            @Parameter(
                    name = "dish_id",
                    description = "The dish id.",
                    required = true,
                    example = "7089a5b2-f7a2-4629-bffe-a5fd88dcb2e2",
                    in = ParameterIn.PATH,
                    schema = @Schema(description = "uuid", type = SchemaType.STRING)
            )
            @PathParam("dish_id")
            final UUID dishId);
}
