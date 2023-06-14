package com.github.joaoprg.ubereats.clone.register.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter(onMethod = @__({@Schema(hidden = true)}))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "restaurant_create", description = "Information of restaurant to be created.")
public class RestaurantCreate {

    @Schema(
            required = true,
            description = "Name of the restaurant.",
            example = "John's Grill",
            maxLength = 50
    )
    @NotNull(message = "Name cannot be null")
    @Size(max = 50)
    public String name;

    @Schema(required = true, ref = "address_create")
    @NotNull(message = "Address cannot be null")
    @Valid
    public AddressCreate address;
}
