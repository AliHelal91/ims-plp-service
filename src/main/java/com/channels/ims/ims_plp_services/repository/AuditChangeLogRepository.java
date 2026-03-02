package com.channels.ims.ims_plp_services.repository;

import com.channels.ims.ims_plp_services.entity.tables.AuditChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditChangeLogRepository extends JpaRepository<AuditChangeLog,Long> {
}
