package org.enib.renew.api.rest.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Schema(name = "APIError", description = "Standard APIError structure defined for all responses excepted the 2XX code response  ")
public class APIError {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date errordate;

    private String fishtag;

    private Integer httpcode;

    private String url;

    public Date getErrordate() {
        return errordate;
    }

    public APIError setErrordate(Date errordate) {
        this.errordate = errordate;
        return this;
    }

    public String getFishtag() {
        return fishtag;
    }

    public APIError setFishtag(String fishtag) {
        this.fishtag = fishtag;
        return this;
    }

    public Integer getHttpcode() {
        return httpcode;
    }

    public APIError setHttpcode(Integer httpcode) {
        this.httpcode = httpcode;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public APIError setUrl(String url) {
        this.url = url;
        return this;
    }
}
