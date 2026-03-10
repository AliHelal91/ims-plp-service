package com.channels.ims.plp.dto.model;

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
public class ModelApplicationSummaryResponseDTO {
    private Long id;
    private String uuid;
    private String ecommerceId;
    private String ecommerceUUID;
    private String ecommerceName;
    private String ecommerceNameAr;
    private String applicationSummaryEn;
    private String applicationSummaryAr;
    private Integer modelId;
}
