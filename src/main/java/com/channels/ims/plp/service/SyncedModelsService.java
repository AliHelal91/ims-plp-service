package com.channels.ims.plp.service;

import com.channels.ims.plp.entity.tables.SyncedModels;
import com.channels.ims.plp.enums.SyncStatusEnums;
import com.channels.ims.plp.repository.SyncedModelsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        Optional<SyncedModels> syncedModels = syncedModelsRepository.findByModelCode(modelCode);

        // return True if Synced other return false
        return syncedModels.map(models -> models.getStatus().equals(SyncStatusEnums.SYNCED.name()))
                .orElse(false);

    }
}
