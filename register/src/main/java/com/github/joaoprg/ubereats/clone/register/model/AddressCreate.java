package com.github.joaoprg.ubereats.clone.register.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Data
@Getter(onMethod = @__({@Schema(hidden = true)}))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "address_create", description = "Address of the restaurant to be created.")
public class AddressCreate {

    @Schema(
            required = true,
            description = "Latitude of the given address.",
            example = "38.7223"
    )
    @NotNull(message = "Latitude cannot be null")
    public Double latitude;

    @Schema(
            required = true,
            description = "Longitude of the given address.",
            example = "9.1393"
    )
    @NotNull(message = "Longitude cannot be null")
    public Double longitude;
}
