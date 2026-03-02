package com.channels.ims.ims_plp_services.aspect;


import com.channels.ims.ims_plp_services.dto.log.ServiceLogDTO;
import com.channels.ims.ims_plp_services.config.FilteringRequest;
import com.channels.ims.ims_plp_services.config.SqlContext;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
@RequiredArgsConstructor
public class ServiceLoggingAspect {

    private final Gson gson;

//    private final KafkaProducer kafkaProducer;

    // ThreadLocal to keep each request's log safe in multi-threaded environment
    private final ThreadLocal<ServiceLogDTO> logDTOThreadLocal = new ThreadLocal<>();
    public final ThreadLocal<Integer> operationOrder = new ThreadLocal<>();

    // Pointcut covering all service method
    @Pointcut("execution(* com.channels.ims.ims_plp_services.service..*(..))")
    private void serviceMethods() {
    }

    // Around advice to initialize LogDTO and proceed with method execution
    @Around("serviceMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        // Build Service Log Details with Initial Value
        ServiceLogDTO serviceLogDTO = buildServiceLogDTO(joinPoint);

        // Update Operation Order
        serviceLogDTO = updateOperationOrder(serviceLogDTO);

        logDTOThreadLocal.set(serviceLogDTO);

        try {
            Object result = joinPoint.proceed();
            serviceLogDTO.setResponse(result != null ? result.toString() : null);
            return result;
        } catch (Exception ex) {
            serviceLogDTO = updateExceptionDetails(serviceLogDTO, ex);
            throw ex;
        } finally {

            serviceLogDTO = finallyUpdate(serviceLogDTO);

            System.out.println(gson.toJson(serviceLogDTO));

            // Send To Kafka
//            kafkaProducer.send(SystemConstant.PLM_INTEGRATION_SERVICE_LOG_TOPIC, gson.toJson(serviceLogDTO));

        }
    }

    /**
     * Update the Service Log DTO for The Finally Block
     * adding the Execution Time , End DateTime, SQL Query, Clear the Thread
     *
     * @param serviceLogDTO ServiceLogDTO
     * @return ServiceLogDTO
     */
    private ServiceLogDTO finallyUpdate(ServiceLogDTO serviceLogDTO) {

        // Capture end time and Calculate the Execution Time
        serviceLogDTO.setEndDateTime(LocalDateTime.now());
        serviceLogDTO.setExecutionTimeInMillisec(BigDecimal.valueOf(Duration.between(serviceLogDTO.getStartDateTime(),
                serviceLogDTO.getEndDateTime()).toNanos() / 1_000_000.0).longValue());

        // capture SQL statements after execution
        List<String> sqlStatements = SqlContext.getAll();
        serviceLogDTO.setTargetDatabaseRequest(sqlStatements);

        // Clear the SQL Thread
        SqlContext.clear();

        // Clear the Log Thread
        logDTOThreadLocal.remove();

        operationOrder.remove();

        return serviceLogDTO;
    }

    /**
     * Update the Service Log DTO Details with the Exception Details
     *
     * @param serviceLogDTO ServiceLogDTO
     * @param ex            Exception
     * @return ServiceLogDTO
     */
    private ServiceLogDTO updateExceptionDetails(ServiceLogDTO serviceLogDTO, Exception ex) {
        serviceLogDTO.setErrorMessage(ex.getMessage());
        serviceLogDTO.setStackTrace(Arrays.toString(ex.getStackTrace()));
        serviceLogDTO.setErrorType(ex.getClass().getSimpleName());
        serviceLogDTO.setSuccessful(Boolean.FALSE);
        serviceLogDTO.setException(Boolean.TRUE);
        return serviceLogDTO;
    }

    /**
     * Update the Operation Order Sequence Details
     *
     * @param serviceLogDTO ServiceLogDTO
     * @return ServiceLogDTO
     */
    private ServiceLogDTO updateOperationOrder(ServiceLogDTO serviceLogDTO) {

        if (FilteringRequest.operationOrder.get() != null) {
            // Set The Operation Order
            int nextOrder = FilteringRequest.operationOrder.get() + 1;
            FilteringRequest.operationOrder.set(nextOrder);
            serviceLogDTO.setSequenceNumber(nextOrder);
        } else {
            operationOrder.set(0);
            int nextOrder = operationOrder.get() + 1;
            FilteringRequest.operationOrder.set(nextOrder);
            serviceLogDTO.setSequenceNumber(nextOrder);
        }
        return serviceLogDTO;
    }

    /**
     * Build Initial Service Log DTO
     *
     * @param joinPoint ProceedingJoinPoint
     * @return ServiceLogDTO
     */
    public ServiceLogDTO buildServiceLogDTO(ProceedingJoinPoint joinPoint) {
        return ServiceLogDTO.builder()
                .startDateTime(LocalDateTime.now())
                .packageName(joinPoint.getTarget().getClass().getPackage().getName())
                .traceId(FilteringRequest.requestId.get() == null ? "" : FilteringRequest.requestId.get())
                .serviceName(AopUtils.getTargetClass(joinPoint.getTarget()).getSimpleName())
                .methodParam(Arrays.toString(joinPoint.getArgs()))
                .methodName(joinPoint.getSignature().getName())
                .successful(Boolean.TRUE)
                .exception(Boolean.FALSE)
                .build();

    }
}
