package com.channels.ims.plp.service;

import com.channels.ims.plp.repository.SyncSchedulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SyncSchedulesService {

    private final SyncSchedulesRepository syncSchedulesRepository;
}
