package com.channels.ims.plp.dto.prs.create.response;

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
public class PrsCreateErrorResponse {

    private String httpStatusCode;
    private STCErrorDetails   error;
}
