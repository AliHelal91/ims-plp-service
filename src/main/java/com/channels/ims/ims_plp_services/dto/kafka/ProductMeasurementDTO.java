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
public class ProductMeasurementDTO {

    private Double length;
    private Double width;
    private Double height;
    private Double weight;
    private String system;
}
