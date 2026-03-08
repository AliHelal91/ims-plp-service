package com.channels.ims.plp.dto.log;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ControllerLogDTO {

    private String url;
    private String requestMethod;
    private String header;
    private String requestBody;
    private String requestParam;
    private String response;
    private String serviceName;
    private String serviceNameUrl;
    private Integer httpsStatusCode;
    private String errorMessage;
    private String source;
    private String destination;
    private String stackTrace;
    private Date createdDate;
    private String userUuid;
    private Long executionTime;
    private String traceId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
