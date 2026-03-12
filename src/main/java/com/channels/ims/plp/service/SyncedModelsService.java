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
     * check if Model is Synced Before
     *
     * @param modelId Model id
     * @return Boolean
     */
    public boolean isModelSynced(Integer modelId) {

        Optional<SyncedModels> syncedModels = syncedModelsRepository.findByModelId(modelId);

        if (syncedModels.isEmpty()) {
            return false;
        }

        if (syncedModels.get().getStatus().equals(SyncStatusEnums.SYNCED.name())) {
            return true;
        }

        return false;
    }
}
