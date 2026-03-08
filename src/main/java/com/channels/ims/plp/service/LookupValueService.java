package com.channels.ims.plp.service;

import com.channels.ims.plp.repository.LookupValueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LookupValueService {

    private final LookupValueRepository lookupValueRepository;
}
