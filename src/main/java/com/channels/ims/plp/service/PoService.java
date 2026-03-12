package com.channels.ims.plp.service;

import com.channels.ims.plp.constant.SystemConstant;
import com.channels.ims.plp.dto.po.stc.create.ConfigSpecCharValueUseDTO;
import com.channels.ims.plp.dto.po.stc.create.ConfigurationDTO;
import com.channels.ims.plp.dto.po.stc.create.DateRangeDTO;
import com.channels.ims.plp.dto.po.stc.create.IdentifierDTO;
import com.channels.ims.plp.dto.po.stc.create.PriceDTO;
import com.channels.ims.plp.dto.po.stc.create.ProdSpecCharValueUseDTO;
import com.channels.ims.plp.dto.po.stc.create.ProductOfferingDTO;
import com.channels.ims.plp.dto.po.stc.create.ProductOfferingPriceDTO;
import com.channels.ims.plp.dto.po.stc.create.ProductOfferingRequestDTO;
import com.channels.ims.plp.dto.po.stc.create.ProductOfferingResponseDTO;
import com.channels.ims.plp.dto.po.stc.create.ProductSpecificationDTO;
import com.channels.ims.plp.dto.po.stc.create.TitleDTO;
import com.channels.ims.plp.dto.portal.sync.request.ProductDetails;
import com.channels.ims.plp.entity.tables.Products;
import com.channels.ims.plp.entity.tables.Request;
import com.channels.ims.plp.enums.RequestType;
import com.channels.ims.plp.exception.ExceptionKey;
import com.channels.ims.plp.exception.ResourceException;
import com.channels.ims.plp.feign.SecureFeignClient;
import com.channels.ims.plp.util.Utils;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class PoService {

    private final ProductsService productsService;
    private final RequestService requestService;
    private final SecureFeignClient secureFeignClient;
    private final Gson gson;

    public void createPORequest(ProductDetails productDetails,
                                Locale locale) {

        // Get Product Details from the Product Master Table
        Products products = productsService.findByProductId(productDetails.getProductId(), locale);

        // Create Request with Type PO_CREATE and Status INITIAL
        Request request = requestService.createRequest(RequestType.PO_CREATE, locale);

        // Prepare the STC PO Create Request
        ProductOfferingRequestDTO productOfferingRequestDTO = preparePORequest(products,productDetails, request, locale);

        request.setRequestPayload(gson.toJson(productOfferingRequestDTO));

        // Call the STC Sync
        request = syncWithSTC(productOfferingRequestDTO, request);

    }

    private ProductOfferingRequestDTO preparePORequest(Products products,
                                                       ProductDetails productDetails,
                                                       Request request,
                                                       Locale locale) {

        return ProductOfferingRequestDTO.builder()
                .imsProjectId(request.getImsProductId().toString())
                .manualTesting(SystemConstant.MANUAL_TESTING_VALUE)
                .productOffering(getProductOffering(products,productDetails))
                .build();

    }

    private List<ProductOfferingDTO> getProductOffering(Products products,ProductDetails productDetails) {

        // ToDo need  Implement
        List<ProductOfferingDTO> productOfferingDTOList = new ArrayList<>();
        productOfferingDTOList.add(ProductOfferingDTO.builder()
                .title(getProductTitlesData(products))
                .availableFor(DateRangeDTO.builder()
                        .startDateTime(productDetails.getAvailableFor())
                        .build())
                .validFor(DateRangeDTO.builder()
                        .endDateTime(productDetails.getValidFor())
                        .build())
                .shippable(productDetails.getShippable())
                .productSpecification(ProductSpecificationDTO.builder()
                        .id(products.getProductId().toString())
                        .name(products.getNameEN())
                        .build())
                .prodSpecCharValueUse(getProdSpecCharValueUse(products))
                .configuration(getProductConfiguration(products))
                .productOfferingPriceArr(getProductOfferingPrice(products))
                .taxCategory(null)
                .build());

        return productOfferingDTOList;
    }

    private List<ProductOfferingPriceDTO> getProductOfferingPrice(Products products) {

        // ToDo need  Implement
        List<ProductOfferingPriceDTO> productOfferingPriceDTOS = new ArrayList<>();
        productOfferingPriceDTOS.add(ProductOfferingPriceDTO.builder()
                .priceType(null)
                .isPromo(null)
                .price(PriceDTO.builder()
                        .unit(null)
                        .value(null)
                        .build())
                .identifier(getPriceIdentifier(products))
                .validFor(DateRangeDTO.builder()
                        .endDateTime(null)
                        .build())
                .title(getPriceTitle())
                .taxCategory(null)
                .build());
        return productOfferingPriceDTOS;
    }

    private List<TitleDTO> getPriceTitle() {

        List<TitleDTO> titleDTOS = new ArrayList<>();
        titleDTOS.add(TitleDTO.builder()
                .text(null)
                .language(SystemConstant.EN_LANGUAGE_CODE)
                .build());

        titleDTOS.add(TitleDTO.builder()
                .text(null)
                .language(SystemConstant.AR_LANGUAGE_CODE)
                .build());

        return titleDTOS;
    }

    private List<IdentifierDTO> getPriceIdentifier(Products products) {

        // ToDo need  Implement
        List<IdentifierDTO> identifierDTOS = new ArrayList<>();
        identifierDTOS.add(IdentifierDTO.builder()
                .identifierType(null)
                .identifier(null)
                .build());
        return identifierDTOS;
    }

    private List<ConfigurationDTO> getProductConfiguration(Products products) {

        // ToDo need  Implement
        List<ConfigurationDTO> configurationDTOS = new ArrayList<>();

        configurationDTOS.add(ConfigurationDTO.builder()
                .configurationId(null)
                .configSpecCharValueUse(getConfigSpecCharValue(products))
                .build());
        return configurationDTOS;
    }

    private List<ConfigSpecCharValueUseDTO> getConfigSpecCharValue(Products products) {
        // ToDo need  Implement
        List<ConfigSpecCharValueUseDTO> configSpecCharValueUseDTOS = new ArrayList<>();

        configSpecCharValueUseDTOS.add(ConfigSpecCharValueUseDTO.builder()
                .id(null)
                .configSpecCharacteristicValue(null)
                .build());

        return configSpecCharValueUseDTOS;

    }

    private List<ProdSpecCharValueUseDTO> getProdSpecCharValueUse(Products products) {

        // ToDo need  Implement
        List<ProdSpecCharValueUseDTO> prodSpecCharValueUseDTOS = new ArrayList<>();
        prodSpecCharValueUseDTOS.add(ProdSpecCharValueUseDTO.builder()
                .id(null)
                .productSpecCharacteristicValue(null)

                .build());
        return prodSpecCharValueUseDTOS;
    }


    /**
     * Prepare the Product tile
     * @param products Products
     * @return TitleDTO List
     */
    private List<TitleDTO> getProductTitlesData(Products products) {

        List<TitleDTO> titleDTOS = new ArrayList<>();
        titleDTOS.add(TitleDTO.builder()
                .text(products.getNameEN())
                .language(SystemConstant.EN_LANGUAGE_CODE)
                .build());

        titleDTOS.add(TitleDTO.builder()
                .text(products.getNameAR())
                .language(SystemConstant.AR_LANGUAGE_CODE)
                .build());

        return titleDTOS;
    }

    private Request syncWithSTC(ProductOfferingRequestDTO prsCreateRequest, Request request) {

        try {
            // Call the STC Sync
            ProductOfferingResponseDTO response = secureFeignClient.createPO(prsCreateRequest);

            // Update Request Details after the Call
            return requestService.updateAfterCompleteSync(request, gson.toJson(response));
        } catch (ResourceException e) {
            // Map the Error After Call
            return requestService.updateAfterErrorAppear(request,
                    e.getMessage(),
                    String.valueOf(e.getStatus().value()),
                    e.getErrorType());
        } catch (Exception e) {

            // Map any Exception apper
            return requestService.updateAfterErrorAppear(request,
                    e.getMessage(),
                    String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    ExceptionKey.INTERNAL_SERVER_ERROR);
        }
    }
}
