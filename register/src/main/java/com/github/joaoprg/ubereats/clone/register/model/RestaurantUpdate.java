package com.github.joaoprg.ubereats.clone.register.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Data
@Getter(onMethod = @__({@Schema(hidden = true)}))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "restaurant_update", description = "Restaurant information to be updated.")
public class RestaurantUpdate {

    @Schema(
            required = true,
            description = "Owner's name.",
            example = "John",
            maxLength = 50
    )
    public String owner;

    @Schema(
            required = true,
            description = "Name of the restaurant.",
            example = "John's Grill",
            maxLength = 50
    )
    @Size(max = 50)
    public String name;

    @Schema(ref = "address_read")
    @Valid
    public AddressRead address;
}
