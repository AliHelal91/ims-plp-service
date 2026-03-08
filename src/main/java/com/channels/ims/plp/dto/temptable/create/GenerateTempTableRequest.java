package com.channels.ims.plp.dto.temptable.create;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@EqualsAndHashCode
public class GenerateTempTableRequest {

//    @NotNull(message = "GenerateTempTableRequest.code.NotNull")
//    @NotEmpty(message = "GenerateTempTableRequest.code.NotEmpty")
    private String code;

//    @NotNull(message = "GenerateTempTableRequest.name.NotNull")
//    @NotEmpty(message = "GenerateTempTableRequest.name.NotEmpty")
    private String name;
}
