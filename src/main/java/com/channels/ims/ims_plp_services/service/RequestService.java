package com.channels.ims.ims_plp_services.service;

import com.channels.ims.ims_plp_services.repository.RequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {

    private final RequestRepository requestRepository;
}
