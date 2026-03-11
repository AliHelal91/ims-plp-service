package com.channels.ims.plp.service;

import com.channels.ims.plp.entity.tables.GeneralConfiguration;
import com.channels.ims.plp.entity.tables.Request;
import com.channels.ims.plp.enums.GeneralConfigurationCodeEnums;
import com.channels.ims.plp.enums.RequestType;
import com.channels.ims.plp.enums.SyncStatusEnums;
import com.channels.ims.plp.repository.RequestRepository;
import com.channels.ims.plp.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
    private final GeneralConfigurationService generalConfigurationService;


    /**
     * Save Request To Database
     *
     * @param request Request Entity
     * @return Request
     */
    public Request save(Request request) {
        return requestRepository.save(request);
    }


    /**
     * Prepare and Create The Request Details
     *
     * @param requestType     requestType
     * @param locale          locale
     * @return Request
     */
    public Request createRequest(RequestType requestType,
                                 Locale locale) {

        // Fetch the Max Retry Config
        GeneralConfiguration maxRetryConfig = generalConfigurationService
                .finalByCode(GeneralConfigurationCodeEnums.MAX_RETRY.name(), locale);


        // Prepare the Request Details
        Request request = Request.builder()
                .requestType(requestType)
                .imsProductId(UUID.randomUUID())
                .responsePayload(null) // it will be updated after the call
                .status(SyncStatusEnums.INITIAL)
                .errorType(null)  // it will be updated if error appear
                .errorCode(null)  // it will be updated if error appear
                .errorMessage(null) // it will be updated if error appear
                .retryCount(0) // the initial Value for retry Count
                .maxRetries(Utils.convertToInteger(maxRetryConfig.getValue(), locale))
                .nextRetryAt(getNextRetry(locale))
                .startedAt(LocalDateTime.now())
                .completedAt(null)  // it will be updated after the Sync Complete
                .build();

        // Save the Request Details
        return requestRepository.save(request);
    }

    /**
     * Find The Next Retry Time
     *
     * @param locale Locale
     * @return LocalDateTime
     */
    private LocalDateTime getNextRetry(Locale locale) {

        // Fetch the Value of Next Retry Config (in minutes)
        GeneralConfiguration nextRetryConfig = generalConfigurationService
                .finalByCode(GeneralConfigurationCodeEnums.RETRY_AFTER.name(), locale);

        // Return the Local Date time plus the next Retry Minutes
        return LocalDateTime.now().plusMinutes(Utils.convertToInteger(nextRetryConfig.getValue(), locale));
    }


    /**
     * Update The Request Details after Sync complete
     *
     * @param request         Request
     * @param responsePayload Response Payload
     * @return Request
     */
    public Request updateAfterCompleteSync(Request request,
                                           String responsePayload) {

        request.setCompletedAt(LocalDateTime.now());
        request.setResponsePayload(responsePayload);
        request.setUpdatedAt(LocalDateTime.now());
        request.setStatus(SyncStatusEnums.SYNCED);

        return requestRepository.save(request);

    }


    /**
     * Update Request Details After Error Appear
     *
     * @param request      Request
     * @param errorMessage Error Message
     * @param errorCode    Error Code
     * @param errorType    Error Type
     * @return Request
     */
    public Request updateAfterErrorAppear(Request request,
                                          String errorMessage,
                                          String errorCode,
                                          String errorType) {

        request.setErrorCode(errorCode);
        request.setErrorType(errorType);
        request.setErrorMessage(errorMessage);
        request.setCompletedAt(LocalDateTime.now());
        request.setUpdatedAt(LocalDateTime.now());
        request.setStatus(SyncStatusEnums.FAILED);

        return requestRepository.save(request);

    }
}
