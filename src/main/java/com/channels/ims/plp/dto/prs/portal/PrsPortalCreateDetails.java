//package com.channels.ims.plp.dto.prs.portal;
//
//import jakarta.validation.Valid;
//import jakarta.validation.constraints.NotEmpty;
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import lombok.ToString;
//
//import java.util.List;
//import java.util.Map;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@ToString
//@Builder
//public class PrsPortalCreateDetails {
//
//    @NotNull(message = "PrsPortalCreateDetails.modelId.NotNull")
//    @NotEmpty(message = "PrsPortalCreateDetails.modelId.NotEmpty")
//    private Integer modelId;
//
//    @NotNull(message = "PrsPortalCreateDetails.modelNameEN.NotNull")
//    @NotEmpty(message = "PrsPortalCreateDetails.modelNameEN.NotEmpty")
//    private String modelNameEN;
//
//    @NotNull(message = "PrsPortalCreateDetails.modelNameAr.NotNull")
//    @NotEmpty(message = "PrsPortalCreateDetails.modelNameAr.NotEmpty")
//    private String modelNameAr;
//
//    @NotNull(message = "PrsPortalCreateDetails.brandCode.NotNull")
//    @NotEmpty(message = "PrsPortalCreateDetails.brandCode.NotEmpty")
//    private String brandCode;
//
//    @Valid
//    private PrsClassificationDetails classificationDetails;
//
//    @NotNull(message = "PrsPortalCreateDetails.filters.NotNull")
//    @NotEmpty(message = "PrsPortalCreateDetails.filters.NotEmpty")
//    private List<String> filters;
//
//    @NotNull(message = "PrsPortalCreateDetails.sections.NotNull")
//    @NotEmpty(message = "PrsPortalCreateDetails.sections.NotEmpty")
//    private Map<String, List<String>> sections;
//
//    @NotNull(message = "PrsPortalCreateDetails.sortPosition.NotNull")
//    @NotEmpty(message = "PrsPortalCreateDetails.sortPosition.NotEmpty")
//    private String sortPosition;
//
//    @NotNull(message = "PrsPortalCreateDetails.isFeatured.NotNull")
//    @NotEmpty(message = "PrsPortalCreateDetails.isFeatured.NotEmpty")
//    private Boolean isFeatured;
//
//    @NotNull(message = "PrsPortalCreateDetails.isRenewed.NotNull")
//    @NotEmpty(message = "PrsPortalCreateDetails.isRenewed.NotEmpty")
//    private Boolean isRenewed;
//
//    @NotNull(message = "PrsPortalCreateDetails.isPreorder.NotNull")
//    @NotEmpty(message = "PrsPortalCreateDetails.isPreorder.NotEmpty")
//    private Boolean isPreorder;
//
//    @NotNull(message = "PrsPortalCreateDetails.releaseDate.NotNull")
//    @NotEmpty(message = "PrsPortalCreateDetails.releaseDate.NotEmpty")
//    private String releaseDate;
//
//    @NotNull(message = "PrsPortalCreateDetails.modelImage.NotNull")
//    @NotEmpty(message = "PrsPortalCreateDetails.modelImage.NotEmpty")
//    private String modelImage;
//
//    @NotNull(message = "PrsPortalCreateDetails.modelVideo.NotNull")
//    @NotEmpty(message = "PrsPortalCreateDetails.modelVideo.NotEmpty")
//    private String modelVideo;
//
//    @NotNull(message = "PrsPortalCreateDetails.availableDate.NotNull")
//    @NotEmpty(message = "PrsPortalCreateDetails.availableDate.NotEmpty")
//    private String availableDate;
//
//    @NotNull(message = "PrsPortalCreateDetails.validForDate.NotNull")
//    @NotEmpty(message = "PrsPortalCreateDetails.validForDate.NotEmpty")
//    private String validForDate;
//
//    @NotNull(message = "PrsPortalCreateDetails.productType.NotNull")
//    @NotEmpty(message = "PrsPortalCreateDetails.productType.NotEmpty")
//    private String productType;
//}
