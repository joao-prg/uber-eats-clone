package com.github.joaoprg.ubereats.clone.register.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.json.bind.annotation.JsonbProperty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(readOnly = true)
public class ExceptionPayload {

    public String code;
    public String message;
    @JsonbProperty("http_status_code")
    public int httpStatusCode;
}
