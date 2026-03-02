package com.channels.ims.ims_plp_services.dto.temptable.fetch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class TempTableDetails {

    private String name;
    private String code;
    private String creationDate;
}
