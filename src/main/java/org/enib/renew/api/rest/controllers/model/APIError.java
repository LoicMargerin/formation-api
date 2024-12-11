package org.enib.renew.api.rest.controllers.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Schema(name = "APIError", description = "Standard APIError structure defined for all responses excepted the 2XX code response  ")
public class APIError {

    private Integer httpcode;

    private String cause;

    public Integer getHttpcode() {
        return httpcode;
    }

    public APIError setHttpcode(Integer httpcode) {
        this.httpcode = httpcode;
        return this;
    }

    public String getCause() {
        return cause;
    }

    public APIError setCause(String cause) {
        this.cause = cause;
        return this;
    }
}
