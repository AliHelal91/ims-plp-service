package com.channels.ims.plp.dto.prs.create.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PrsCreateResponse {

    private String projectName;
    private String summary;
    private String manualTesting;
    private String imsProjectId;
    private String projectId;

}
