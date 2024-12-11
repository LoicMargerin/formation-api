package org.enib.renew.exceptions;

import org.enib.renew.api.rest.controllers.model.APIError;

public class APIErrorException extends RuntimeException {
    private static final long serialVersionUID = 2751380441133246111L;
    private final APIError apierror;

    public APIErrorException(APIError error) {
        this.apierror = error;
    }

    public APIError getError() {
        return this.apierror;
    }

    public String toString() {
        return "APIErrorException [apierror=" + this.apierror + "]";
    }
}
