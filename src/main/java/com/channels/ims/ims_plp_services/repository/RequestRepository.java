package com.channels.ims.ims_plp_services.repository;

import com.channels.ims.ims_plp_services.entity.tables.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request,Long> {
}
