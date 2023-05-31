package com.github.joaoprg.ubereats.clone.register.resource;

import com.github.joaoprg.ubereats.clone.register.exception.ExceptionPayload;
import com.github.joaoprg.ubereats.clone.register.model.DishCreate;
import com.github.joaoprg.ubereats.clone.register.model.DishList;
import com.github.joaoprg.ubereats.clone.register.model.DishRead;
import com.github.joaoprg.ubereats.clone.register.model.DishUpdate;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantCreate;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantList;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantRead;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantUpdate;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
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
                    schema = @Schema(implementation = RestaurantCreate.class)
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
                            schema = @Schema(implementation = RestaurantRead.class))
            ),
            @APIResponse(
                    name = "badRequest",
                    responseCode = "400",
                    description = "Bad request.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.BAD_REQUEST
                    )
            ),
            @APIResponse(
                    name = "unauthorized",
                    responseCode = "401",
                    description = "Unauthorized access - invalid or unverifiable JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.UNAUTHORIZED
                    )
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.FORBIDDEN
                    )
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal server error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.INTERNAL_SERVER_ERROR
                    )
            )
    })
    Response createRestaurant(
            @NotNull
            @Valid
            final RestaurantCreate restaurantCreate,
            @Context UriInfo uriInfo);

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
                            schema = @Schema(implementation = RestaurantList.class))
            ),
            @APIResponse(
                    name = "badRequest",
                    responseCode = "400",
                    description = "Bad request.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.BAD_REQUEST
                    )
            ),
            @APIResponse(
                    name = "unauthorized",
                    responseCode = "401",
                    description = "Unauthorized access - invalid or unverifiable JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.UNAUTHORIZED
                    )
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.FORBIDDEN
                    )
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal server error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.INTERNAL_SERVER_ERROR
                    )
            )
    })
    Response readRestaurants(
            @Parameter(
                    name = "page",
                    description = "The page requested.",
                    example = "1",
                    in = ParameterIn.QUERY,
                    schema = @Schema(
                            type = SchemaType.INTEGER,
                            defaultValue = "1",
                            minimum = "1"
                    )
            )
            @QueryParam("page")
            Integer page,
            @Parameter(
                    name = "per_page",
                    description = "The maximum amount of dishes to be returned per page.",
                    example = "50",
                    in = ParameterIn.QUERY,
                    schema = @Schema(
                            type = SchemaType.INTEGER,
                            defaultValue = "50",
                            minimum = "1",
                            maximum = "200"
                    )
            )
            @QueryParam("per_page")
            Integer perPage,
            @Context UriInfo uriInfo);

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
                    schema = @Schema(implementation = RestaurantUpdate.class)
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
                            schema = @Schema(implementation = RestaurantRead.class))
            ),
            @APIResponse(
                    name = "badRequest",
                    responseCode = "400",
                    description = "Bad request.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.BAD_REQUEST
                    )
            ),
            @APIResponse(
                    name = "unauthorized",
                    responseCode = "401",
                    description = "Unauthorized access - invalid or unverifiable JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.UNAUTHORIZED
                    )
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.FORBIDDEN
                    )
            ),
            @APIResponse(
                    name = "notFound",
                    responseCode = "404",
                    description = "Restaurant not found.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.NOT_FOUND
                    )
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal server error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.INTERNAL_SERVER_ERROR
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
            final RestaurantUpdate restaurantUpdate,
            @Context UriInfo uriInfo);

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
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.UNAUTHORIZED
                    )
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.FORBIDDEN
                    )
            ),
            @APIResponse(
                    name = "notFound",
                    responseCode = "404",
                    description = "Restaurant not found.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.NOT_FOUND
                    )
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal server error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.INTERNAL_SERVER_ERROR
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
            final UUID restaurantId,
            @Context UriInfo uriInfo);


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
                    schema = @Schema(implementation = DishCreate.class)
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
                            schema = @Schema(implementation = DishRead.class))
            ),
            @APIResponse(
                    name = "badRequest",
                    responseCode = "400",
                    description = "Bad request.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.BAD_REQUEST
                    )
            ),
            @APIResponse(
                    name = "unauthorized",
                    responseCode = "401",
                    description = "Unauthorized access - invalid or unverifiable JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.UNAUTHORIZED
                    )
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.FORBIDDEN
                    )
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal server error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.INTERNAL_SERVER_ERROR
                    )
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
            final DishCreate dishCreate,
            @Context UriInfo uriInfo);

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
                            schema = @Schema(implementation = DishList.class))
            ),
            @APIResponse(
                    name = "badRequest",
                    responseCode = "400",
                    description = "Bad request.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.BAD_REQUEST
                    )
            ),
            @APIResponse(
                    name = "unauthorized",
                    responseCode = "401",
                    description = "Unauthorized access - invalid or unverifiable JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.UNAUTHORIZED
                    )
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.FORBIDDEN
                    )
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal server error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.INTERNAL_SERVER_ERROR
                    )
            )
    })
    Response readDishes(
            @Parameter(
                    name = "page",
                    description = "The page requested.",
                    example = "1",
                    in = ParameterIn.QUERY,
                    schema = @Schema(
                            type = SchemaType.INTEGER,
                            defaultValue = "1",
                            minimum = "1"
                    )
            )
            @QueryParam("page")
            Integer page,
            @Parameter(
                    name = "per_page",
                    description = "The maximum amount of dishes to be returned per page.",
                    example = "50",
                    in = ParameterIn.QUERY,
                    schema = @Schema(
                            type = SchemaType.INTEGER,
                            defaultValue = "50",
                            minimum = "1",
                            maximum = "200"
                    )
            )
            @QueryParam("per_page")
            Integer perPage,
            @Context UriInfo uriInfo);


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
                            schema = @Schema(implementation = DishList.class))
            ),
            @APIResponse(
                    name = "badRequest",
                    responseCode = "400",
                    description = "Bad request.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.BAD_REQUEST
                    )
            ),
            @APIResponse(
                    name = "unauthorized",
                    responseCode = "401",
                    description = "Unauthorized access - invalid or unverifiable JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.UNAUTHORIZED
                    )
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.FORBIDDEN
                    )
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal server error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.INTERNAL_SERVER_ERROR
                    )
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
            final UUID restaurantId,
            @Parameter(
                    name = "page",
                    description = "The page requested.",
                    example = "1",
                    in = ParameterIn.QUERY,
                    schema = @Schema(
                            type = SchemaType.INTEGER,
                            defaultValue = "1",
                            minimum = "1"
                    )
            )
            @QueryParam("page")
            Integer page,
            @Parameter(
                    name = "per_page",
                    description = "The maximum amount of dishes to be returned per page.",
                    example = "50",
                    in = ParameterIn.QUERY,
                    schema = @Schema(
                            type = SchemaType.INTEGER,
                            defaultValue = "50",
                            minimum = "1",
                            maximum = "200"
                    )
            )
            @QueryParam("per_page")
            Integer perPage,
            @Context UriInfo uriInfo);


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
                    schema = @Schema(implementation = DishUpdate.class)
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
                            schema = @Schema(implementation = DishRead.class))
            ),
            @APIResponse(
                    name = "badRequest",
                    responseCode = "400",
                    description = "Bad request.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.BAD_REQUEST
                    )
            ),
            @APIResponse(
                    name = "unauthorized",
                    responseCode = "401",
                    description = "Unauthorized access - invalid or unverifiable JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.UNAUTHORIZED
                    )
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.FORBIDDEN
                    )
            ),
            @APIResponse(
                    name = "notFound",
                    responseCode = "404",
                    description = "Dish not found.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.NOT_FOUND
                    )
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal server error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.INTERNAL_SERVER_ERROR
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
            final DishUpdate dishUpdate,
            @Context UriInfo uriInfo);

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
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.UNAUTHORIZED
                    )
            ),
            @APIResponse(
                    name = "forbidden",
                    responseCode = "403",
                    description = "Forbidden access - can't find the required scope in the JWT.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.FORBIDDEN
                    )
            ),
            @APIResponse(
                    name = "notFound",
                    responseCode = "404",
                    description = "Dish not found.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.NOT_FOUND
                    )
            ),
            @APIResponse(
                    name = "internalError",
                    responseCode = "500",
                    description = "Internal server error.",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = ExceptionPayload.class),
                            example = ApiResponseExampleConstants.INTERNAL_SERVER_ERROR
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
            final UUID dishId,
            @Context UriInfo uriInfo);
}
