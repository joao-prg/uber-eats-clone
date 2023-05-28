package com.github.joaoprg.ubereats.clone.authentication.model;

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

@Schema(
        description = "Embedded list of dishes.",
        readOnly = true
)
public class DishEmbeddedList {
    @JsonbProperty("dishes")
    @Schema(description = "List of dishes.")
    private List<DishRead> dishes = new ArrayList<>();
}