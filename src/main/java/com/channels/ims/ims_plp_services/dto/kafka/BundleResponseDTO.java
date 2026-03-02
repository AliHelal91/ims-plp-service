package com.channels.ims.ims_plp_services.dto.kafka;

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
public class BundleResponseDTO {

    private Integer productId;
    private String productName;
    private String productType;
    private Integer quantity;
}
