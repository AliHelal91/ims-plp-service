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
public class ModelSpecificationResponseDTO {
    private Long id;
    private String titleEn;
    private String titleValueEn;
    private String titleAr;
    private String titleValueAr;
    private String uuid;
    private Integer modelId;
}
