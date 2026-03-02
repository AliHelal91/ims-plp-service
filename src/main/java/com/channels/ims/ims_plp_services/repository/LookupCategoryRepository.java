package com.channels.ims.ims_plp_services.repository;

import com.channels.ims.ims_plp_services.entity.lookup.LookupCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LookupCategoryRepository extends JpaRepository<LookupCategory,Long> {
}
