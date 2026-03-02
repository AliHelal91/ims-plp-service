package com.channels.ims.ims_plp_services.service;

import com.channels.ims.ims_plp_services.repository.LookupValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LookupValueService {

    private final LookupValueRepository lookupValueRepository;
}
