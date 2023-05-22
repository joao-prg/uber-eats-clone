package com.github.joaoprg.ubereats.clone.authentication.model;

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

    @PositiveOrZero
    public Double price;
}