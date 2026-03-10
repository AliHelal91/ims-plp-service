package com.channels.ims.plp.service;

import com.channels.ims.plp.dto.prs.create.request.PrsCreateRequest;
import com.channels.ims.plp.entity.tables.Request;
import com.channels.ims.plp.entity.tables.SyncSchedules;
import com.channels.ims.plp.repository.SyncSchedulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SyncSchedulesService {

    private final SyncSchedulesRepository syncSchedulesRepository;

    public SyncSchedules createSyncRequest(PrsCreateRequest prsCreateRequest,
                                           Request request) {

        return null;
    }
}
