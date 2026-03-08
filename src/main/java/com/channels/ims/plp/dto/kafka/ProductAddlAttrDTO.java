package com.channels.ims.plp.dto.kafka;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ProductAddlAttrDTO {
    private String modelSummaryEn;
    private String modelSummaryAr;
    private List<ModelSpecificationDTO> specAr;
    private List<ModelSpecificationDTO> specEn;
    private List<ProductImageResponse> imageUrlsEn;
    private List<ProductImageResponse> imageUrlsAr;
    @JsonProperty("parentCatalogNameEN")
    private String parentCatalogNameEn;
    @JsonProperty("parentCatalogNameAR")
    private String parentCatalogNameAr;
    @JsonProperty("childCatalogNameEN")
    private String childCatalogNameEn;
    @JsonProperty("childCatalogNameAR")
    private String childCatalogNameAr;
    private String descriptionAr;
    private String createdDate;
    private String updatedDate;
    @JsonProperty("attribute")
    private JsonNode productAttribute;
}
