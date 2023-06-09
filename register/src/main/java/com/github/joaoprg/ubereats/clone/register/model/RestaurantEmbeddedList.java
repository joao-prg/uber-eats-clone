package com.github.joaoprg.ubereats.clone.register.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import java.util.ArrayList;
import java.util.List;

@Data
@Getter(onMethod = @__({@Schema(hidden = true)}))
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "restaurant_embedded_list", description = "The list of restaurants.")
public class RestaurantEmbeddedList {

    @JsonbProperty("restaurants")
    @Schema(description = "List of restaurants.", ref = "restaurant_read")
    private List<RestaurantRead> restaurants = new ArrayList<>();
}