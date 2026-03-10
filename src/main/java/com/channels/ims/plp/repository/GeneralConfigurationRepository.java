package com.channels.ims.plp.repository;

import com.channels.ims.plp.entity.tables.GeneralConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GeneralConfigurationRepository extends JpaRepository<GeneralConfiguration, Long> {

    Optional<GeneralConfiguration> findByCodeAndStatus(String code, String Status);
}
