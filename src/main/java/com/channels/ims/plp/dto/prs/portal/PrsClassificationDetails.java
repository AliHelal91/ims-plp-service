package com.channels.ims.plp.dto.prs.portal;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class PrsClassificationDetails {

    @NotNull(message = "PrsClassificationDetails.category.NotNull")
    @NotEmpty(message = "PrsClassificationDetails.category.NotEmpty")
    private String category;

    @NotNull(message = "PrsClassificationDetails.subCategory.NotNull")
    @NotEmpty(message = "PrsClassificationDetails.subCategory.NotEmpty")
    private String subCategory;

    @NotNull(message = "PrsClassificationDetails.year.NotNull")
    @NotEmpty(message = "PrsClassificationDetails.year.NotEmpty")
    private String year;
}
