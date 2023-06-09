package com.github.joaoprg.ubereats.clone.register.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.UUID;

@Data
@Getter(onMethod = @__({@Schema(hidden = true)}))
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "restaurant_read", description = "Restaurant information.")
public class RestaurantRead {

    @Schema(
            required = true,
            description = "Owner's name.",
            example = "John",
            maxLength = 50
    )
    @NotBlank(message = "Owner cannot be blank")
    public String owner;

    @Schema(
            required = true,
            description = "Name of the restaurant.",
            example = "John's Grill",
            maxLength = 50
    )
    @NotNull(message = "Name cannot be null")
    @Size(max = 50)
    public String name;

    @Schema(required = true, ref = "address_read")
    @NotNull(message = "Address cannot be null")
    @Valid
    public AddressRead address;

    @JsonbProperty("created_at")
    @Schema(
            required = true,
            description = "Creation date of the restaurant entry.",
            example = "12/02/2023 18:31:23",
            maxLength = 50
    )
    @PastOrPresent
    public String createdAt;

    @Schema(
            required = true,
            description = "The restaurant identifier.",
            example = "f67e429c-ddf3-427f-8503-7afee054ae14"
    )
    @NotNull(message = "Id cannot be null")
    private UUID id;
}
