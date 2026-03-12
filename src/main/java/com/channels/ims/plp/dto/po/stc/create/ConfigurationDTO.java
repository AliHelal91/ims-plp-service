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
public class ConfigurationDTO {

    private String configurationId;
    private List<ConfigSpecCharValueUseDTO> configSpecCharValueUse;
}
