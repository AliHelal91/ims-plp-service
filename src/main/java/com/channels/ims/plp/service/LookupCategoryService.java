package com.channels.ims.plp.service;

import com.channels.ims.plp.repository.LookupCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LookupCategoryService {

    private final LookupCategoryRepository lookupCategoryRepository;
}
