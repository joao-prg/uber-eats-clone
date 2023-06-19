package com.github.joaoprg.ubereats.clone.register;

import com.github.joaoprg.ubereats.clone.register.exception.ExceptionPayload;
import com.github.joaoprg.ubereats.clone.register.model.DishCreate;
import com.github.joaoprg.ubereats.clone.register.model.DishList;
import com.github.joaoprg.ubereats.clone.register.model.DishRead;
import com.github.joaoprg.ubereats.clone.register.model.DishUpdate;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantCreate;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantList;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantRead;
import com.github.joaoprg.ubereats.clone.register.model.RestaurantUpdate;
import org.eclipse.microprofile.openapi.annotations.Components;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        tags = @Tag(name = "Ubereats clone Register"),
        info = @Info(
                title = "Ubereats clone Register API",
                version = "0.0.1"
        ),
        components = @Components(
                schemas = {
                        @Schema(name = "exception", implementation = ExceptionPayload.class),
                        @Schema(name = "dish_create", implementation = DishCreate.class),
                        @Schema(name = "dish_read", implementation = DishRead.class),
                        @Schema(name = "dish_update", implementation = DishUpdate.class),
                        @Schema(name = "restaurant_create", implementation = RestaurantCreate.class),
                        @Schema(name = "restaurant_read", implementation = RestaurantRead.class),
                        @Schema(name = "restaurant_update", implementation = RestaurantUpdate.class),
                        @Schema(name = "dish_list", implementation = DishList.class),
                        @Schema(name = "restaurant_list", implementation = RestaurantList.class)
                }
        )
)
public class RegisterApplication extends Application {
}
