package com.github.joaoprg.ubereats.clone.authentication.model;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Builder
@Getter(onMethod = @__({@Schema(hidden = true)}))
@NoArgsConstructor
@AllArgsConstructor
@JsonbPropertyOrder({
        "total",
        "count",
        "page",
        "perPage",
        "restaurantEmbeddedList"
})

@Schema(
        description = "A list of restaurants.",
        readOnly = true
)
public class RestaurantList {
    @Schema(
            description = "Total number of restaurants.",
            example = "1"
    )
    private int total;

    @Schema(
            description = "Number of restaurants in the page.",
            example = "1"
    )
    private int count;

    @Schema(
            description = "Page number.",
            example = "1"
    )
    private int page;

    @JsonbProperty("per_page")
    @Schema(
            description = "Number of restaurants per page.",
            example = "10"
    )
    private int perPage;

    @JsonbProperty("_embedded")
    @Schema(description = "The list of restaurants.")
    private RestaurantEmbeddedList restaurantEmbeddedList;
}