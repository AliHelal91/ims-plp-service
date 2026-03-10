package com.channels.ims.plp.dto.model;

import com.fasterxml.jackson.databind.JsonNode;
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
public class ModelDetailsResponse {

    private Integer id;
    private String nameEn;
    private String nameAr;
    private BrandResponseDTO brandInfo;
    private String code;
    private String countryCode;
    private String summaryEn;
    private String summaryAr;
    private JsonNode attribute;
    private List<ModelSpecificationResponseDTO> modelSpecifications;
    private List<ModelApplicationSummaryResponseDTO> applicationSummaries;

}
