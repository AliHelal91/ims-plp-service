package com.channels.ims.plp.repository;

import com.channels.ims.plp.entity.tables.SyncSchedules;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SyncSchedulesRepository extends JpaRepository<SyncSchedules, UUID> {
}
