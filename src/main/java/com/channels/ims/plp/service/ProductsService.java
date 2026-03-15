package com.channels.ims.plp.service;

import com.channels.ims.plp.dto.kafka.ProductDTO;
import com.channels.ims.plp.entity.tables.Products;
import com.channels.ims.plp.enums.AuditTriggeredSystemEnums;
import com.channels.ims.plp.enums.ChangeAction;
import com.channels.ims.plp.enums.EntityTypeEnums;
import com.channels.ims.plp.enums.SyncStatusEnums;
import com.channels.ims.plp.exception.ExceptionKey;
import com.channels.ims.plp.exception.ResourceException;
import com.channels.ims.plp.mapper.ProductDetailsMapper;
import com.channels.ims.plp.repository.ProductsRepository;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;
    private final Gson gson;
    private final ProductDetailsMapper productDetailsMapper;
    private final AuditChangeLogService auditChangeLogService;

    /**
     * Save or Update Product fetched From ims-product-service
     *
     * @param payload Product Details
     */
    public void saveProductMasterDetails(String payload) {

        // convert Payload to Product DTO
        ProductDTO productDTO = gson.fromJson(payload, ProductDTO.class);

        // check if Product Exists
        Products originalProduct = findByProductId(productDTO.getProductId(), Locale.getDefault());

        // Map to Product Entity in case not exists create new one
        Products updatedProduct = productDetailsMapper.mapToProducts(originalProduct, productDTO);

        // save to Product Table
        updatedProduct = productsRepository.save(updatedProduct);

        // Save Change To Audit Table
        auditChangeLogService.saveChange(
                updatedProduct,
                originalProduct,
                EntityTypeEnums.PRODUCT,
                updatedProduct.getId(),
                ChangeAction.UPDATE,
                AuditTriggeredSystemEnums.KAFKA_LISTENERS,
                originalProduct.getRequest());

    }

    /**
     * Find Product By Product ID
     *
     * @param productId Product ID
     * @param locale    Locale
     * @return Product
     */
    public Products findByProductId(Integer productId, Locale locale) {

        // Fetch Product from
        return productsRepository.findByProductId(productId).orElseThrow(() ->
                new ResourceException(ExceptionKey.PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND, locale));

    }
}
