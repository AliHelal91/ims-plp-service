package com.channels.ims.plp.dto.general;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private Timestamp timestamp;
    private Integer status;
    private String error;
    private String errorCode;
    private String message;
    private List<String> errors;

}
