package com.github.joaoprg.ubereats.clone.register.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Getter(onMethod = @__({@Schema(hidden = true)}))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "dish_read", description = "Dish information.")
public class DishRead {

    @Schema(
            required = true,
            description = "The dish identifier.",
            example = "6c3bc19e-c442-4b5c-a69b-2c1f8877a703"
    )
    @NotNull(message = "Id cannot be null")
    private UUID id;

    @Schema(
            required = true,
            description = "Name of the dish.",
            example = "Shrimp fried rice.",
            maxLength = 50
    )
    @NotNull(message = "Name cannot be null")
    @Size(max = 50)
    public String name;

    @Schema(
            description = "Description of the dish.",
            example = "Vietnamese shrimp fried with basmati rice.",
            maxLength = 200
    )
    @Size(max = 200)
    public String description;

    @Schema(
            required = true,
            description = "The restaurant identifier.",
            example = "f67e429c-ddf3-427f-8503-7afee054ae14"
    )
    @NotNull(message = "Restaurant Id cannot be null")
    @JsonbProperty("restaurant_id")
    public UUID restaurantId;

    @Schema(
            required = true,
            description = "Price of the dish.",
            example = "5.50",
            minimum = "0"
    )
    @PositiveOrZero
    public Double price;
}