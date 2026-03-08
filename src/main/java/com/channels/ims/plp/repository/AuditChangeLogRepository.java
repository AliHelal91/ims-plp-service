package com.channels.ims.plp.repository;

import com.channels.ims.plp.entity.tables.AuditChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditChangeLogRepository extends JpaRepository<AuditChangeLog,Long> {
}
