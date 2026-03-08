package com.channels.ims.plp.mapper;

import com.channels.ims.plp.constant.SystemConstant;
import com.channels.ims.plp.dto.kafka.ProductAddlAttrDTO;
import com.channels.ims.plp.dto.kafka.ProductDTO;
import com.channels.ims.plp.entity.tables.Products;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDetailsMapper {

    private final ObjectMapper objectMapper;

    public Products mapToProducts(Products product, ProductDTO productDTO) {

        if (product == null) {
            product = new Products();
        }

        product.setProductId(productDTO.getProductId());
        product.setNameAR(productDTO.getNameAr());
        product.setNameEN(productDTO.getNameEn());
        product.setDescriptionEN(productDTO.getDescription());
//        product.setProductStatus(productDTO.getInventoryStatus());
        product.setItemCode(productDTO.getItemCode());
//        product.setVatCategory(productDTO.getVatCategory());
//        product.setVatValue(productDTO.getVatValue() != null ? productDTO.getVatValue()/100 : null);
//        product.setErpInventoryItemId(productDTO.getErpInventoryItemId());
        product.setProductCategory(productDTO.getParentCategory());
        product.setProductSubCategory(productDTO.getChildCategory());
//        product.setBusinessGroupCode(productDTO.getBusinessGroupCode());
//        product.setVendorItemCode(productDTO.getVendorItemCode());
//        product.setIsBundled(productDTO.getIsBundled());
//        product.setBrandAr(productDTO.getBrandAr());
        product.setItemCode(productDTO.getBrandEn());
//        product.setModelAr(productDTO.getModelAr());
//        product.setModelEn(productDTO.getModelEn());
//        product.setColorAr(productDTO.getColorAr());
//        product.setColorEn(productDTO.getColorEn());
//        product.setCapacityEn(productDTO.getCapacityEn());
//        product.setBarCode(productDTO.getBarCode());
        product.setIsSerialized(productDTO.getIsSerialized());
//        product.setInventoryStatus(productDTO.getInventoryStatus());
        product.setProductType(productDTO.getProductType());
        product.setCountryCode(productDTO.getCountryCode() != null ? productDTO.getCountryCode()
                : SystemConstant.COUNTRY_CODE_SA);
        product.setAttributes(productDTO.getAdditionalAttribute() != null ? productDTO.getAdditionalAttribute()
                : objectMapper.createArrayNode());
        setAddlAttributes(product, productDTO);


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
