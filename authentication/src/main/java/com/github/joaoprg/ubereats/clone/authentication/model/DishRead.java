package com.github.joaoprg.ubereats.clone.authentication.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Getter(onMethod = @__({@Schema(hidden = true)}))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "The dish to be read.")
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
            description = "Information about the restaurant."
    )
    @NotNull(message = "Restaurant cannot be null")
    @Valid
    public RestaurantRead restaurant;

    @Schema(
            required = true,
            description = "Price of the dish.",
            example = "5.50",
            minimum = "0"
    )
    @PositiveOrZero
    public Double price;
}