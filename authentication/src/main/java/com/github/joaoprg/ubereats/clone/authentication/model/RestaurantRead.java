package com.github.joaoprg.ubereats.clone.authentication.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

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

public class RestaurantRead {

    @NotNull(message = "Id cannot be null")
    private UUID id;

    @NotBlank(message = "Owner cannot be blank")
    public String owner;

    @NotNull(message = "Name cannot be null")
    @Size(max = 50)
    public String name;

    @Valid
    public AddressRead address;

    @PastOrPresent
    public String createdAt;

}
