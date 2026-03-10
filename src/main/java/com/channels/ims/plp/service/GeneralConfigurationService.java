package com.channels.ims.plp.service;

import com.channels.ims.plp.entity.tables.GeneralConfiguration;
import com.channels.ims.plp.enums.GeneralConfigurationStatusEnums;
import com.channels.ims.plp.exception.ExceptionKey;
import com.channels.ims.plp.exception.ResourceException;
import com.channels.ims.plp.repository.GeneralConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class GeneralConfigurationService {

    private final GeneralConfigurationRepository generalConfigurationRepository;

    /**
     * Find Active Configuration by Code
     *
     * @param code   Configuration Code
     * @param locale Local
     * @return GeneralConfiguration
     */
    public GeneralConfiguration finalByCode(String code, Locale locale) {

        // Find Active Configuration By Code OR Throw Exception
        return generalConfigurationRepository.findByCodeAndStatus(code, GeneralConfigurationStatusEnums.ACTIVE.name())
                .orElseThrow(() ->
                        new ResourceException(ExceptionKey.CONFIGURATION_NOT_FOUND, HttpStatus.NOT_FOUND, locale)
                );

    }
}
