package com.channels.ims.plp.service;

import com.channels.ims.plp.dto.portal.sync.request.PortalSyncProductRequest;
import com.channels.ims.plp.dto.portal.sync.request.ProductDetails;
import com.channels.ims.plp.dto.prs.portal.PrsClassificationDetails;
import com.channels.ims.plp.dto.prs.portal.PrsPortalCreateRequest;
import com.channels.ims.plp.dto.prs.portal.PrsPortalCreateResponse;
import com.channels.ims.plp.entity.tables.Products;
import com.channels.ims.plp.enums.SyncStatusEnums;
import com.channels.ims.plp.exception.ExceptionKey;
import com.channels.ims.plp.exception.ResourceException;
import com.channels.ims.plp.util.Utils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PortalService {

    private final ProductsService productsService;
    private final SyncedModelsService syncedModelsService;
    private final PrsService prsService;
    private final PoService poService;

    public void stcSync(HttpServletRequest httpServletRequest,
                        PortalSyncProductRequest portalSyncProductRequest) {

        // Fetch Request Language
        Locale locale = Utils.getLocale(httpServletRequest);

        for (ProductDetails productDetail : portalSyncProductRequest.getProductDetails()) {

            // Find Product By Product ID fetch from Table Product Master
            Products product = productsService.findByProductId(productDetail.getProductId(), locale);

            // Validate Product Values (Sync Status, available Date, valid Date)
            validateProduct(product, productDetail, locale);

            // Check if Model(PRS) is Synced or not
            if (!syncedModelsService.isModelSynced(product.getModelCode())) {

                // Synce the Model First (PRS Request)
                PrsPortalCreateResponse prsResponse = prsService.createPrs(
                        preparePrsRequest(product.getModelId(), productDetail), locale);


                if (prsResponse.getStatus().equals(SyncStatusEnums.SYNCED.name())) {
                    // Sync the Product Details with STC (PO Request)
                    poService.createPORequest(productDetail, locale);
                }

            } else {
                // Sync the Product Details with STC (PO Request)
                poService.createPORequest(productDetail, locale);
            }


        }


    }

    /**
     * Prepare the Create PRS Request
     *
     * @param modelId       Model Id
     * @param productDetail Product Details
     * @return PrsPortalCreateRequest
     */
    private PrsPortalCreateRequest preparePrsRequest(Integer modelId,
                                                     ProductDetails productDetail) {

        return PrsPortalCreateRequest.builder()
                .modelId(modelId)
                .classificationDetails(PrsClassificationDetails.builder()
                        .category(productDetail.getModelCategory())
                        .subCategory(productDetail.getModelSubCategory())
                        .build())
                .filters(productDetail.getModelFilters())
                .sections(productDetail.getModelSections())
                .sortPosition(productDetail.getModelSortPosition())
                .isFeatured(productDetail.getIsFeatured())
                .isRenewed(productDetail.getIsRenewed())
                .isPreorder(productDetail.getIsPreorder())
                .releaseDate(productDetail.getReleaseDate())
                .modelImage(productDetail.getModelImage())
                .modelVideo(productDetail.getModelVideo())
                .availableDate(productDetail.getModelAvailableFor())
                .validForDate(productDetail.getModelValidFor())
                .productType(productDetail.getProductType())
                .build();
    }

    /**
     * Check if Product eligible to Sync with Stc
     *
     * @param product       Product
     * @param productDetail Product Details
     * @param locale        Locale
     */
    private void validateProduct(Products product,
                                 ProductDetails productDetail,
                                 Locale locale) {

        // Check Sync Status
        if (product.getSyncStatus().name().equals(SyncStatusEnums.SYNCED.name())) {
            throw new ResourceException(ExceptionKey.PRODUCT_SYNCED, HttpStatus.FOUND, locale);
        }
    }
}