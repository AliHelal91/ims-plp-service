package com.channels.ims.plp.mapper;

import com.channels.ims.plp.dto.kafka.ProductAddlAttrDTO;
import com.channels.ims.plp.dto.kafka.ProductDTO;
import com.channels.ims.plp.entity.tables.Products;
import com.channels.ims.plp.entity.tables.Request;
import com.channels.ims.plp.enums.SyncStatusEnums;
import com.channels.ims.plp.util.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ProductDetailsMapper {

    private final ObjectMapper objectMapper;

    public Products mapToProducts(Products product, ProductDTO productDTO) {

        if (product == null) {
            product = new Products();
        }
        product.setProductId(productDTO.getProductId());
        product.setItemCode(productDTO.getItemCode());
        product.setNameEN(productDTO.getNameEn());
        product.setNameAR(productDTO.getNameAr());
        product.setModelId(null);  // ToDo needed from ims-product-details
        product.setModelCode(null);// ToDo needed from ims-product-details
        product.setDescriptionEN(productDTO.getDescription());
        product.setDescriptionAR(productDTO.getDescriptionAr());
        product.setProductCategory(productDTO.getProductTypeCategory()); // ToDo need to Validate
        product.setProductSubCategory(productDTO.getProductSubTypeCategory()); // ToDo need to Validate
        product.setCountryCode(productDTO.getCountryCode());
        product.setProductType(product.getProductType());
        product.setManufacturer(productDTO.getManufacturer());
        product.setAttributes(productDTO.getAttribute());
        product.setSyncStatus(SyncStatusEnums.PENDING);
        product.setProductVersion(0); // As Initial Value start from 0
        product.setSyncedVersion(0); // As Initial Value start from 0
        product.setLastSyncedAt(null); // should be null Product not Synced yet
        product.setLastSyncedAt(null); // should be null Product not Synced yet
        product.setSalesChannels(null); // ToDo needed from ims-product-detail
        product. setIsPreorder(null);  // Fetch from Enrichment Data
        product.setIsSerialized(productDTO.getIsSerialized());
        product.setReturnAllowed(null); // Fetch from Enrichment Data
        product.setFreeShippingEnabled(null); // Fetch from Enrichment Data
        product.setInventoryCheck(Utils.convertToBoolean(productDTO.getInventoryStatus()));
        product.setAllowNonStc(null); // Fetch from Enrichment Data
        product.setPublishDate(null); // will be updated after Sync
        product.setAvailableDate(null); // will be updated after Sync
        product.setDiscontinuedDate(null); // ToDo needed from ims-product-detail
        product.setPreorderDate(null);// ToDo needed from ims-product-detail
        product.setRequest(null); // will be updated after Sync

        return product;
    }

    private void setAddlAttributes(Products productDetails, ProductDTO productDTO) {
        ProductAddlAttrDTO prodAttrs = objectMapper.convertValue(productDTO.getAdditionalAttribute(),
                ProductAddlAttrDTO.class);
        if (prodAttrs == null) {
            prodAttrs = new ProductAddlAttrDTO();
        }
        if (productDTO.getDescriptionAr() != null) {
            prodAttrs.setDescriptionAr(productDTO.getDescriptionAr());
        }
        if (productDTO.getCreatedDate() != null) {
            prodAttrs.setCreatedDate(productDTO.getCreatedDate());
        }
        if (productDTO.getUpdatedDate() != null) {
            prodAttrs.setUpdatedDate(productDTO.getUpdatedDate());
        }
        if (productDTO.getAttribute() != null) {
            prodAttrs.setProductAttribute(productDTO.getAttribute());
        }
        productDetails.setAttributes(objectMapper.valueToTree(prodAttrs));
    }
}
