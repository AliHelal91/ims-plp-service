package com.channels.ims.plp.dto.prs.portal;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class PrsPortalCreateRequest {

    @NotEmpty(message = "PrsPortalCreateRequest.models.NotEmpty")
    @NotNull(message = "PrsPortalCreateRequest.models.NotNull")
    @Valid
    private List<PrsPortalCreateDetails> models;
}
