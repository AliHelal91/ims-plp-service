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
public class ProductOfferingPriceDTO {
    private String priceType;
    private Boolean isPromo;
    private PriceDTO price;
    private List<IdentifierDTO> identifier;
    private DateRangeDTO validFor;
    private List<TitleDTO> title;
    private String taxCategory;
}
