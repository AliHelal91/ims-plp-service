package com.channels.ims.plp.repository;

import com.channels.ims.plp.entity.tables.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request,Long> {
}
