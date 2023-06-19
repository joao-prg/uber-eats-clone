package com.github.joaoprg.ubereats.clone.register.resource;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ApiResponseExampleConstants {
    final String BAD_REQUEST = "{\"code\": \"1040000\", \"message\": \"Bad request.\", \"http_status_code\": 400}";

    final String UNAUTHORIZED = "{\"code\": \"1040100\", \"message\": \"Unauthorized access.\", \"http_status_code\": 401}";

    final String FORBIDDEN = "{\"code\": \"1040300\", \"message\": \"Forbidden access.\", \"http_status_code\": 403}";

    final String NOT_FOUND = "{\"code\": \"1040400\", \"message\": \"Not found.\", \"http_status_code\": 404}";

    final String INTERNAL_SERVER_ERROR = "{\"code\": \"1050000\", \"message\": \"Internal server error.\", " +
            "\"http_status_code\": 500}";
}
