package com.channels.ims.ims_plp_services.service;

import com.channels.ims.ims_plp_services.repository.SyncSchedulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SyncSchedulesService {

    private final SyncSchedulesRepository syncSchedulesRepository;
}
