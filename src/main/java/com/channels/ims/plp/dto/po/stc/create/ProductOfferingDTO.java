package com.channels.ims.plp.dto.po.stc.create;

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
public class ProductOfferingDTO {

    private List<TitleDTO> title;
    private DateRangeDTO availableFor;
    private DateRangeDTO validFor;
    private Boolean shippable;
    private ProductSpecificationDTO productSpecification;
    private List<ProdSpecCharValueUseDTO> prodSpecCharValueUse;
    private List<ConfigurationDTO> configuration;
    private List<ProductOfferingPriceDTO> productOfferingPriceArr;
    private String taxCategory;
}
