package com.channels.ims.ims_plp_services.repository;

import com.channels.ims.ims_plp_services.entity.lookup.LookupValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LookupValueRepository extends JpaRepository<LookupValue,Long> {
}
