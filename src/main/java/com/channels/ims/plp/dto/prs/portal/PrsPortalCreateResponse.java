package com.channels.ims.plp.dto.prs.portal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PrsPortalCreateResponse {

    private String status;
    private String requestId;
    private LocalDateTime completedAt;
}
