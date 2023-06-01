package com.github.joaoprg.ubereats.clone.register.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@Getter(onMethod = @__({@Schema(hidden = true)}))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "dish_create", description = "Dish to be created.")
public class DishCreate {

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
            description = "Price of the dish.",
            example = "5.50",
            minimum = "0"
    )
    @PositiveOrZero
    public Double price;
}
