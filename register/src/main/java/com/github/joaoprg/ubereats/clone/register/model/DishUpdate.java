package com.github.joaoprg.ubereats.clone.register.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.PositiveOrZero;

@Data
@Getter(onMethod = @__({@Schema(hidden = true)}))
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DishUpdate {

    @Schema(
            required = true,
            description = "Price of the dish.",
            example = "5.50",
            minimum = "0"
    )
    @PositiveOrZero
    public Double price;
}