package com.channels.ims.plp.service;

import com.channels.ims.plp.entity.tables.Request;
import com.channels.ims.plp.entity.tables.SyncedModels;
import com.channels.ims.plp.enums.SyncStatusEnums;
import com.channels.ims.plp.exception.ExceptionKey;
import com.channels.ims.plp.exception.ResourceException;
import com.channels.ims.plp.repository.SyncedModelsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SyncedModelsService {

    private final SyncedModelsRepository syncedModelsRepository;


    /**
     * check if Model is Synced Before or Not
     *
     * @param modelCode Model Code
     * @return Boolean
     */
    public boolean isModelSynced(String modelCode) {

        // Find Synced Model by Model Code
        Optional<SyncedModels> syncedModels = syncedModelsRepository.findByModelCodeAndStatus(modelCode,
                SyncStatusEnums.SYNCED.name());

        // return True if Synced other return false
        return syncedModels.map(models -> models.getStatus().equals(SyncStatusEnums.SYNCED.name()))
                .orElse(false);

    }


    /**
     * Create Synced Model (PRS Create) with Request
     *
     * @param modelId   Model ID form ims-product-service
     * @param modelCode Model code form ims-product-service
     * @param request   Prs Create Request
     * @param locale    Locale
     */
    public void createSyncedModel(Integer modelId,
                                  String modelCode,
                                  Request request,
                                  Locale locale) {

        // Check if models (Create PRS) Synced Before
        if (syncedModelsRepository.existsByModelIdAndRequestAndStatus(modelId,
                request, SyncStatusEnums.SYNCED.name())) {
            throw new ResourceException(ExceptionKey.MODEL_SYNCED, HttpStatus.FOUND, locale);
        }

        // Create new synced Model with Status Initial
        syncedModelsRepository.save(SyncedModels.builder()
                .status(SyncStatusEnums.INITIAL.name())
                .modelId(modelId)
                .modelCode(modelCode)
                .request(request)
                .build());

    }


    /**
     * Update the Synced Model Status Depends on Request Status
     *
     * @param modelCode Model Code
     * @param request   Request
     * @param locale    Locale
     */
    public void updateSyncedModelStatus(String modelCode, Request request, Locale locale) {

        // Find Synced Model by Model Code and Initial Status
        Optional<SyncedModels> syncedModelsOpt = syncedModelsRepository.findByModelCodeAndStatus(modelCode,
                SyncStatusEnums.INITIAL.name());

        // check if synced Model exists
        if (syncedModelsOpt.isEmpty()) {
            throw new ResourceException(ExceptionKey.SYNCED_MODEL_NOT_FOUND, HttpStatus.NOT_FOUND, locale);
        }

        SyncedModels syncedModels = syncedModelsOpt.get();

        // Update Synced Model Status and UpdateAt Time
        syncedModels.setStatus(request.getStatus().name());
        syncedModels.setUpdatedAt(LocalDateTime.now());

        // Save the Update
        syncedModelsRepository.save(syncedModels);
    }
}
