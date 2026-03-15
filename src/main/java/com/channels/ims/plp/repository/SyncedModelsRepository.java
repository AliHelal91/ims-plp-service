package com.channels.ims.plp.repository;

import com.channels.ims.plp.entity.tables.Request;
import com.channels.ims.plp.entity.tables.SyncedModels;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SyncedModelsRepository extends JpaRepository<SyncedModels,Long> {

    Optional<SyncedModels> findByModelCodeAndStatus(String modelCode,String status);

    Boolean existsByModelIdAndRequestAndStatus(Integer modelId, Request request,String status);
}
