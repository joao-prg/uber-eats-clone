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

    private String code;
    private String message;
    @JsonbProperty("http_status_code")
    private int httpStatusCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<FieldException> fieldExceptions;

    @Data
    @JsonAutoDetect
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FieldException {

        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String path;
        private String message;
    }
}
