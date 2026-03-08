package com.channels.ims.plp.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
public class ResourceException extends RuntimeException {

    private String message;
    private HttpStatus status;
    private Locale locale;

}
