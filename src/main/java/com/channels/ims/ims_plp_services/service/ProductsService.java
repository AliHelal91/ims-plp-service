package com.channels.ims.ims_plp_services.service;

import com.channels.ims.ims_plp_services.dto.kafka.ProductDTO;
import com.channels.ims.ims_plp_services.entity.tables.Products;
import com.channels.ims.ims_plp_services.enums.AuditTriggeredSystemEnums;
import com.channels.ims.ims_plp_services.enums.ChangeAction;
import com.channels.ims.ims_plp_services.enums.EntityTypeEnums;
import com.channels.ims.ims_plp_services.enums.SyncStatusEnums;
import com.channels.ims.ims_plp_services.mapper.ProductDetailsMapper;
import com.channels.ims.ims_plp_services.repository.ProductsRepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductsService {

    private final ProductsRepository productsRepository;
    private final Gson gson;
    private final ProductDetailsMapper productDetailsMapper;
    private final AuditChangeLogService auditChangeLogService;

    public void saveProductMasterDetails(String payload) {

        // convert Payload to Product DTO
        ProductDTO productDTO = gson.fromJson(payload, ProductDTO.class);

        // check if Product Exists
        Products originalProduct = productsRepository.findByProductId(productDTO.getProductId());

        // Map to Product Entity in case not exists create new one
        Products updatedProduct = productDetailsMapper.mapToProducts(originalProduct, productDTO);

        // update Sync Details
        updatedProduct = updateSyncDetails(updatedProduct);

        // save to Product Table
        updatedProduct = productsRepository.save(updatedProduct);

        // Save Change To Audit Table
        auditChangeLogService.saveChange(
                updatedProduct,
                originalProduct,
                EntityTypeEnums.PRODUCT,
                updatedProduct.getId(),
                ChangeAction.UPDATE,
                AuditTriggeredSystemEnums.KAFKA_LISTENERS);

    }

    private Products updateSyncDetails(Products product) {

        product.setProductVersion(1);
        product.setSyncedVersion(0);
        product.setSyncStatus(SyncStatusEnums.PENDING);
        return product;
    }
}
