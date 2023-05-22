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

public class DishRead {

    @NotNull(message = "Id cannot be null")
    private UUID id;

    @NotNull(message = "Name cannot be null")
    @Size(max = 50)
    public String name;

    @Size(max = 200)
    public String description;

    @Valid
    @NotNull(message = "Restaurant cannot be null")
    public RestaurantRead restaurant;

    @PositiveOrZero
    public Double price;
}