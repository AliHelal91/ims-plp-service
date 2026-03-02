package com.channels.ims.ims_plp_services.dto.log;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ServiceLogDTO {

    private String packageName;
    private String methodParam;
    private String methodName;
    private String response;
    private String serviceName;
    private String errorMessage;
    private String stackTrace;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String traceId;
    private String error;
    private Boolean exception;
    private List<String> targetDatabaseRequest;
    private Long executionTimeInMillisec;
    private Integer sequenceNumber;
    private String errorType;
    private Boolean successful;

}
