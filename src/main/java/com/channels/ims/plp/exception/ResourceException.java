package com.channels.ims.plp.exception;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Locale;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceException extends RuntimeException {

    private String message;
    private HttpStatus status;
    private Locale locale;
    private String errorType;

    public ResourceException(String message, HttpStatus status, Locale locale) {
        this.message = message;
        this.status = status;
        this.locale = locale;
    }

    public ResourceException(String message, HttpStatus status, Locale locale, String errorType) {
        this.message = message;
        this.status = status;
        this.locale = locale;
        this.errorType = errorType;
    }
}
