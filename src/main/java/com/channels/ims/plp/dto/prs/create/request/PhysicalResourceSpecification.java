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
public class PhysicalResourceSpecification {

    private String name;
    private String productType;
    private ProductSpecification productSpecification;
    private List<ProdSpecCharValueUse> prodSpecCharValueUse;
    private List<TitleDetails> title;
    private SpecificDate validFor;
    private SpecificDate availableFor;

}
