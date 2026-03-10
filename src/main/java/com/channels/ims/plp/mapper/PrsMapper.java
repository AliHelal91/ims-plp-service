package com.channels.ims.plp.mapper;

import com.channels.ims.plp.dto.prs.portal.PrsPortalCreateResponse;
import com.channels.ims.plp.entity.tables.Request;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PrsMapper {


    /**
     * Map PRS Portal Create Response
     *
     * @param request Request
     * @return Response
     */
    public PrsPortalCreateResponse prsCreateResponse(Request request) {
        return PrsPortalCreateResponse.builder()
                .status(request.getStatus().name())
                .requestId(request.getId().toString())
                .completedAt(request.getCompletedAt())
                .build();
    }
}
