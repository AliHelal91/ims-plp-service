//package com.channels.ims.plp.aspect;
//
//import com.channels.ims.plp.config.FilteringRequest;
//import com.channels.ims.plp.constant.SystemConstant;
//import com.channels.ims.plp.dto.log.ControllerLogDTO;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.gson.Gson;
//import io.micrometer.common.util.StringUtils;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.aop.support.AopUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import java.math.BigDecimal;
//import java.time.Duration;
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Date;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Aspect
//@Component
//@RequiredArgsConstructor
//public class ControllerLoggingAspect {
//
//    @Value("${spring.application.name}")
//    private String appName;
//    private final Gson gson;
//    private final HttpServletRequest httpServletRequest;
//    private final ObjectMapper objectMapper;
//
//    // Pointcut covering all Controller Method
//    @Pointcut("execution(* com.channels.ims.ims_plp_services.controller..*(..))")
//    private void controllerPointCut() {
//    }
//
//    // Around advice to initialize LogDTO and proceed with method execution
//    @Around("controllerPointCut()")
//    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
//
//        // Build Service Log Details with Initial Value
//        ControllerLogDTO controllerLogDTO = buildControllerLogDTO(joinPoint);
//
//        try {
//            Object result = joinPoint.proceed();
//
//            // Update Controller Log Details with Response Result
//            controllerLogDTO.setResponse(result != null ? result.toString() : null);
//            controllerLogDTO.setHttpsStatusCode(getHttpStatusCode(joinPoint));
//            return result;
//        } catch (Exception ex) {
//
//            // Update Controller Log DTO with Exception Details
//            controllerLogDTO = updateExceptionDetails(controllerLogDTO, ex);
//            throw ex;
//
//        } finally {
//
//            controllerLogDTO = finallyUpdate(controllerLogDTO);
//
//            System.out.println(gson.toJson(controllerLogDTO));
//
//            // Send To Kafka
////            kafkaProducer.send(SystemConstant.PLM_INTEGRATION_SERVICE_LOG_TOPIC, gson.toJson(serviceLogDTO));
//
//        }
//    }
//
//    private Integer getHttpStatusCode(ProceedingJoinPoint joinPoint) {
//
//        ServletRequestAttributes attributes =
//                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//
//        HttpServletResponse response = attributes.getResponse();
//        return response.getStatus();
//    }
//
//    private ControllerLogDTO finallyUpdate(ControllerLogDTO controllerLogDTO) {
//        controllerLogDTO.setEndDateTime(LocalDateTime.now());
//        controllerLogDTO.setExecutionTime(BigDecimal.valueOf(Duration.between(controllerLogDTO.getStartDateTime(),
//                controllerLogDTO.getEndDateTime()).toNanos() / 1_000_000.0).longValue());
//        return controllerLogDTO;
//    }
//
//    private ControllerLogDTO updateExceptionDetails(ControllerLogDTO controllerLogDTO, Exception ex) {
//
//        controllerLogDTO.setErrorMessage(ex.getMessage());
//        controllerLogDTO.setStackTrace(Arrays.toString(ex.getStackTrace()));
//        return controllerLogDTO;
//    }
//
//    private ControllerLogDTO buildControllerLogDTO(ProceedingJoinPoint joinPoint) throws JsonProcessingException {
//        Map<String, String> headers = Collections.list(httpServletRequest.getHeaderNames()).stream()
//                .collect(Collectors.toMap(h -> h, httpServletRequest::getHeader));
//
//        return ControllerLogDTO.builder()
//                .url(httpServletRequest.getRequestURI())
//                .requestMethod(httpServletRequest.getMethod())
//                .header(String.valueOf(headers))
//                .requestBody(Arrays.toString(joinPoint.getArgs()))
//                .requestParam(objectMapper.writeValueAsString(httpServletRequest.getParameterMap()))
//                .serviceName(AopUtils.getTargetClass(joinPoint.getTarget()).getSimpleName())
//                .serviceNameUrl(httpServletRequest.getRequestURI())
//                .source(getSourceName(httpServletRequest))
//                .destination(appName)
//                .createdDate(new Date())
//                .userUuid(headers.get(SystemConstant.USER_UUID))
//                .traceId(FilteringRequest.requestId.get())
//                .startDateTime(LocalDateTime.now())
//                .build();
//    }
//
//    private String getSourceName(HttpServletRequest httpServletRequest) {
//        String sourceName;
//        if (StringUtils.isNotBlank(httpServletRequest.getHeader(SystemConstant.MICRO_SERVICE_SOURCE_HEADER))) {
//            sourceName = httpServletRequest.getHeader(SystemConstant.MICRO_SERVICE_SOURCE_HEADER);
//        } else if (StringUtils.isNotBlank(httpServletRequest.getHeader(SystemConstant.SOURCE))) {
//            sourceName = httpServletRequest.getHeader(SystemConstant.SOURCE);
//        } else {
//            sourceName = "Source not available";
//        }
//        return sourceName;
//    }
//
//
//}
