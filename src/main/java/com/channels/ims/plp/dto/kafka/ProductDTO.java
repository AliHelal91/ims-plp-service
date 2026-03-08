package com.channels.ims.plp.dto.kafka;

import com.fasterxml.jackson.databind.JsonNode;
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
public class ProductDTO {
    private Integer productId;
    private Integer originalProductId;
    private String description;
    private String itemCode;
    private String productType;
    private String barCode;
    private String glCode;
    private String name;
    private String nameAr;
    private String nameEn;
    private Integer categoryId;
    private String categoryName;
    private String color;
    private String colorAr ;
    private String colorEn ;
    private String storage;
    private String storageAr;
    private String storageEn ;
    private String model;
    private String modelAr ;
    private String modelEn;
    private String manufacturer;
    private String manufacturerAr ;
    private String manufacturerEn ;
    private String unitOfMeasurement;
    private String createdDate;
    private String updatedDate;
    private String createdBy;
    private String updatedBy;
    private Boolean isSerialized;
    private Boolean deviceProtection;
    private List<BundleResponseDTO> bundledProduct;
    private ProductMeasurementDTO productMeasurement;
    private Double vatValue;
    private String vatCategory;
    private Double faceValueCurrencyValue;
    private String faceValueCurrencyCode;
    private String manufacturerItemCode;
    private String businessGroupCategory;
    private String businessGroupCode;
    private String productLineCategory;
    private String productTypeCategory;
    private String parentCategory;
    private String childCategory;
    private String productSubTypeCategory;
    private Integer erpInventoryItemId;
    private String productStatus;
    private String inventoryStatus;
    private Integer shelfLifeAge;
    private String countryCode;
    private String vendorItemCode;
    private Boolean isBundled;
    private String brandAr;
    private String brandEn;
    private String capacityEn;

    private String descriptionAr;
    private JsonNode attribute;
    private Integer parentCatalogId;
    private Integer childCatalogId;
    private String parentCatalog;
    private String childCatalog;
    private String predecessor;
    private Boolean hasPredecessor;
    private JsonNode additionalAttribute;
}
