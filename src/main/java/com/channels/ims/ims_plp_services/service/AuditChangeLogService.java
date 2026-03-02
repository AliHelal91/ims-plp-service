package com.channels.ims.ims_plp_services.service;

import com.channels.ims.ims_plp_services.entity.tables.AuditChangeLog;
import com.channels.ims.ims_plp_services.enums.AuditTriggeredSystemEnums;
import com.channels.ims.ims_plp_services.enums.ChangeAction;
import com.channels.ims.ims_plp_services.enums.EntityTypeEnums;
import com.channels.ims.ims_plp_services.exception.ExceptionKey;
import com.channels.ims.ims_plp_services.exception.ResourceException;
import com.channels.ims.ims_plp_services.repository.AuditChangeLogRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditChangeLogService {

    private final AuditChangeLogRepository auditChangeLogRepository;
    private final ObjectMapper objectMapper;

    /**
     * Save All the Entity Changes in Audit Table
     *
     * @param updatedEntity   updatedEntity
     * @param originalEntity  originalEntity
     * @param entityTypeEnums entityTypeEnums(Products,Request,etc .. )
     * @param entityId        Entity ID
     * @param <T>             Generic Type
     */
    public <T> void saveChange(T updatedEntity,
                               T originalEntity,
                               EntityTypeEnums entityTypeEnums,
                               UUID entityId,
                               ChangeAction changeAction,
                               AuditTriggeredSystemEnums triggeredSystem) {

        if (originalEntity != null) {

            // Set of Ignored Filed
            Set<String> ignored = Set.of("id", "createdAt", "updatedAt");

            // Find All Changed Field
            Map<String, Object[]> changedField = calculateDiff(originalEntity,
                    updatedEntity, ignored, Locale.getDefault());

            // Save in Audit Table
            auditChangeLogRepository.save(AuditChangeLog.builder()
                    .entityType(entityTypeEnums)
                    .entityId(entityId)
                    .action(changeAction)
                    .changeReason(changeAction.name())
                    .changedFields(changedField.keySet().stream().toList())
                    .beforeState(originalEntity.toString())
                    .afterStatus(updatedEntity.toString())
                    .syncBatchId(null)
                    .requestId(null)
                    .sourceSystem("")
                    .triggeredBy(triggeredSystem.name())
                    .build());

        }
    }


    /**
     * Utility Method used for Audit to map the Entity Filed as key and his value
     *
     * @param entity Entity
     * @param <T>    Generic Entity
     * @return Map
     */
    public <T> Map<String, Object> toMap(T entity,
                                         Locale locale) {
        // check if the Entity Value is null
        if (entity == null) {
            throw new ResourceException(ExceptionKey.ENTITY_IS_NULL, HttpStatus.NOT_ACCEPTABLE, locale);
        }

        // Convert the entity into Map<Key,Value>
        return objectMapper.convertValue(entity, new TypeReference<>() {
        });
    }

    /**
     * Utility Method that find All the Effected Filed in the Entity
     *
     * @param beforeObj     entity Before Change
     * @param afterObj      entity After Change
     * @param ignoredFields List of Filed will be ignored in the comparison
     * @param <T>           Generic Entity Type
     * @return MAP
     */
    public <T> Map<String, Object[]> calculateDiff(
            T beforeObj,
            T afterObj,
            Set<String> ignoredFields,
            Locale locale) {

        // initial SET that contain All the Key from ( BeforeObj & AfterObj )
        Set<String> allKeys = new HashSet<>();

        // Map All Field Before the change
        Map<String, Object> before = toMap(beforeObj, locale);

        // Map All Field After the change
        Map<String, Object> after = toMap(afterObj, locale);

        // Add All BeforeObj Key
        allKeys.addAll(before.keySet());

        // Add All AfterObj Key
        allKeys.addAll(after.keySet());

        // Prepare a list of changes
        Map<String, Object[]> changes = new HashMap<>();

        for (String key : allKeys) {

            // Filed name to Ignore from the Comparison
            if (ignoredFields.contains(key)) {
                continue;
            }

            Object oldValue = before.get(key);
            Object newValue = after.get(key);

            // Compare the Filed before and After the change for all Key
            if (!Objects.equals(oldValue, newValue)) {
                changes.put(key, new Object[]{oldValue, newValue});
            }
        }

        // Return the Changes
        return changes;
    }
}
