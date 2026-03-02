package com.channels.ims.ims_plp_services.dto.prs.create;

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
public class ProdSpecCharValueUse {

    private String id;
    private List<ProductSpecCharacteristicValue> productSpecCharacteristicValue;
}
