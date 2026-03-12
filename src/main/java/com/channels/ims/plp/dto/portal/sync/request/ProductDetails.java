package com.channels.ims.plp.dto.portal.sync.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProductDetails {

    private Integer productId;
    private String availableFor;
    private String validFor;
    private String taxCategory;
    private BigDecimal priceValue;
    private String modelCategory;
    private String modelSubCategory;
    private List<String> modelFilters;
    private Map<String, List<String>>  modelSections;
    private String modelSortPosition;
    private Boolean shippable;

    @NotNull(message = "PrsPortalCreateDetails.isFeatured.NotNull")
    @NotEmpty(message = "PrsPortalCreateDetails.isFeatured.NotEmpty")
    private Boolean isFeatured;

    @NotNull(message = "PrsPortalCreateDetails.isRenewed.NotNull")
    @NotEmpty(message = "PrsPortalCreateDetails.isRenewed.NotEmpty")
    private Boolean isRenewed;

    @NotNull(message = "PrsPortalCreateDetails.isPreorder.NotNull")
    @NotEmpty(message = "PrsPortalCreateDetails.isPreorder.NotEmpty")
    private Boolean isPreorder;

    @NotNull(message = "PrsPortalCreateDetails.releaseDate.NotNull")
    @NotEmpty(message = "PrsPortalCreateDetails.releaseDate.NotEmpty")
    private String releaseDate;

    @NotNull(message = "PrsPortalCreateDetails.modelImage.NotNull")
    @NotEmpty(message = "PrsPortalCreateDetails.modelImage.NotEmpty")
    private String modelImage;

    @NotNull(message = "PrsPortalCreateDetails.modelVideo.NotNull")
    @NotEmpty(message = "PrsPortalCreateDetails.modelVideo.NotEmpty")
    private String modelVideo;

    @NotNull(message = "PrsPortalCreateDetails.productType.NotNull")
    @NotEmpty(message = "PrsPortalCreateDetails.productType.NotEmpty")
    private String productType;


    private String modelAvailableFor;
    private String modelValidFor;


}
