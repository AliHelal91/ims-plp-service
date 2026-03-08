package com.channels.ims.plp.dto.general;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class ErrorResponse {

    private String message;
    private String error;
    private Integer status;
    private List<String> errors;

}
