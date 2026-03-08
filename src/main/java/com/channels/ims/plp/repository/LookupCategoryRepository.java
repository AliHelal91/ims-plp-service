package com.channels.ims.plp.repository;

import com.channels.ims.plp.entity.lookup.LookupCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LookupCategoryRepository extends JpaRepository<LookupCategory,Long> {
}
