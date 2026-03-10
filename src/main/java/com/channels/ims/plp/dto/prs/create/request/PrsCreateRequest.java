package com.channels.ims.plp.dto.prs.create.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PrsCreateRequest {

    private String imsProjectId;
    private String manualTesting;
    private List<PhysicalResourceSpecification> physicalResourceSpecification;

}
