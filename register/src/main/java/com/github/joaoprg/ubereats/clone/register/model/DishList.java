package com.github.joaoprg.ubereats.clone.register.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbPropertyOrder;

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
        "dishEmbeddedList"
})
@Schema(name = "dish_list", description = "Page with list of dishes.")
public class DishList {
    @Schema(
            description = "Total number of dishes.",
            example = "1"
    )
    private int total;

    @Schema(
            description = "Number of dishes in the page.",
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
            description = "Number of dishes per page.",
            example = "10"
    )
    private int perPage;

    @JsonbProperty("_embedded")
    @Schema(ref = "dish_embedded_list")
    private DishEmbeddedList dishEmbeddedList;
}