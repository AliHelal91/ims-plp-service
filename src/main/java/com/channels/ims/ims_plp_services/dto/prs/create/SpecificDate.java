package com.channels.ims.ims_plp_services.dto.prs.create;

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
public class SpecificDate {

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
