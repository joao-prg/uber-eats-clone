package com.github.joaoprg.ubereats.clone.register.exception;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionPayload {

    @Schema(description = "Exception code.", example = "1040000")
    private String code;

    @Schema(description = "Exception message.", example = "Invalid input.")
    private String message;

    @Schema(description = "Exception HTTP status code.", example = "400")
    @JsonbProperty("http_status_code")
    private int httpStatusCode;

    @Schema(description = "Exception error fields.", ref = "field_exception")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldException> fieldExceptions;

    @Data
    @JsonAutoDetect
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(name = "field_exception")
    public static class FieldException {

        @Schema(description = "Field path.", example = "dishCreate.name")
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String path;

        @Schema(description = "Field exception message.", example = "Name cannot be null")
        private String message;
    }
}
