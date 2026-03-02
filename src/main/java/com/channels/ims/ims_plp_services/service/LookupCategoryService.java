package com.channels.ims.ims_plp_services.service;

import com.channels.ims.ims_plp_services.repository.LookupCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LookupCategoryService {

    private final LookupCategoryRepository lookupCategoryRepository;
}
