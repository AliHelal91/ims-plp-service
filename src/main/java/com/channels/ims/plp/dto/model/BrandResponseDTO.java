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
public class BrandResponseDTO {
    private Integer id;
    private String nameEn;
    private String nameAr;
    private String code;
    private String countryCode;
}
