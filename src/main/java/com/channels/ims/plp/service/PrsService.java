package com.channels.ims.plp.service;

import com.channels.ims.plp.constant.SystemConstant;
import com.channels.ims.plp.dto.model.ModelDetailsResponse;
import com.channels.ims.plp.dto.model.ModelSpecificationResponseDTO;
import com.channels.ims.plp.dto.prs.create.request.PhysicalResourceSpecification;
import com.channels.ims.plp.dto.prs.create.request.ProdSpecCharValueUse;
import com.channels.ims.plp.dto.prs.create.request.ProductSpecCharacteristicValue;
import com.channels.ims.plp.dto.prs.create.request.ProductSpecification;
import com.channels.ims.plp.dto.prs.create.request.PrsCreateRequest;
import com.channels.ims.plp.dto.prs.create.request.SpecificDate;
import com.channels.ims.plp.dto.prs.create.request.TitleDetails;
import com.channels.ims.plp.dto.prs.create.response.PrsCreateResponse;
import com.channels.ims.plp.dto.prs.portal.PrsPortalCreateDetails;
import com.channels.ims.plp.dto.prs.portal.PrsPortalCreateRequest;
import com.channels.ims.plp.dto.prs.portal.PrsPortalCreateResponse;
import com.channels.ims.plp.entity.tables.Request;
import com.channels.ims.plp.enums.RequestType;
import com.channels.ims.plp.exception.ExceptionKey;
import com.channels.ims.plp.exception.ResourceException;
import com.channels.ims.plp.feign.ModelFeignClient;
import com.channels.ims.plp.feign.SecureFeignClient;
import com.channels.ims.plp.mapper.PrsMapper;
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
public class PrsService {

    private final ModelFeignClient modelFeignClient;
    private final SecureFeignClient secureFeignClient;
    private final RequestService requestService;
    private final Gson gson;
    private final PrsMapper prsMapper;


    /**
     * PRS Portal Create  Service
     *
     * @param prsPortalCreateRequest PrsPortalCreateRequest
     * @param httpServletRequest     HttpServletRequest
     * @return PrsPortalCreateResponse
     */
    public PrsPortalCreateResponse createPrs(PrsPortalCreateRequest prsPortalCreateRequest,
                                             HttpServletRequest httpServletRequest) {

        // Find Request Language
        Locale locale = Utils.getLocale(httpServletRequest);

        // Create Request with the Initial Status (Pending)
        Request request = requestService.createRequest(
                RequestType.PRS_CREATE,
                locale);

        // Prepare the Create PRS with STC Request
        PrsCreateRequest prsCreateRequest = PrsCreateRequest.builder()
                .imsProjectId(request.getImsProductId().toString())
                .manualTesting(SystemConstant.MANUAL_TESTING_VALUE)
                .build();

        List<PhysicalResourceSpecification> physicalResourceSpecification = new ArrayList<>();

        for (PrsPortalCreateDetails model : prsPortalCreateRequest.getModels()) {

            // Find Model Details from the ims-product-service
            ModelDetailsResponse modelDetails = modelFeignClient.getModelDetails(model.getModelId());

            // prepare the list of ProdSpecCharValueUse
            List<ProdSpecCharValueUse> prodSpecCharValueUses = getProdSpecCharValueUse(model, modelDetails);

            // prepare the list of TitleDetails
            List<TitleDetails> titleDetails = getTitleDetails(modelDetails);

            // add to the physicalResourceSpecification list
            physicalResourceSpecification.add(PhysicalResourceSpecification.builder()
                    .name(modelDetails.getNameEn())
                    .productType(model.getProductType())
                    .productSpecification(ProductSpecification.builder()
                            .id(model.getModelId().toString())
                            .name(model.getModelNameEN())
                            .build())
                    .prodSpecCharValueUse(prodSpecCharValueUses)
                    .title(titleDetails)
                    .validFor(SpecificDate.builder()
                            .endDateTime(model.getValidForDate())
                            .build())
                    .availableFor(SpecificDate.builder()
                            .startDateTime(model.getAvailableDate())
                            .build())
                    .build());
        }

        prsCreateRequest.setPhysicalResourceSpecification(physicalResourceSpecification);



        // Sync With STC
        request = syncWithSTC(prsCreateRequest, request);

        // Return Response
        return prsMapper.prsCreateResponse(request);

    }

    /**
     * Sync With STC
     *
     * @param prsCreateRequest PrsCreateRequest
     * @param request          Request
     * @return Request
     */
    private Request syncWithSTC(PrsCreateRequest prsCreateRequest, Request request) {

        try {
            // Call the STC Sync
            PrsCreateResponse response = secureFeignClient.createPRS(prsCreateRequest);

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

    /**
     * Map the Title Details
     *
     * @param modelDetails ModelDetailsResponse
     * @return TitleDetails List
     */
    private List<TitleDetails> getTitleDetails(ModelDetailsResponse modelDetails) {

        List<TitleDetails> titleDetails = new ArrayList<>();

        titleDetails.add(TitleDetails.builder()
                .language(SystemConstant.EN_LANGUAGE_CODE)
                .text(modelDetails.getNameEn())
                .build());

        titleDetails.add(TitleDetails.builder()
                .language(SystemConstant.AR_LANGUAGE_CODE)
                .text(modelDetails.getNameAr())
                .build());

        return titleDetails;
    }

    /**
     * Map the ProdSpecCharValue
     *
     * @param model        PrsPortalCreateDetails
     * @param modelDetails ModelDetailsResponse
     * @return ProdSpecCharValueUse List
     */
    private List<ProdSpecCharValueUse> getProdSpecCharValueUse(PrsPortalCreateDetails model,
                                                               ModelDetailsResponse modelDetails) {
        List<ProdSpecCharValueUse> prodSpecCharValueUses = new ArrayList<>();
        for (ModelSpecificationResponseDTO modelSpecification : modelDetails.getModelSpecifications()) {

            List<ProductSpecCharacteristicValue> productSpecCharacteristicValues = new ArrayList<>();
            productSpecCharacteristicValues.add(ProductSpecCharacteristicValue.builder()
                    .value(modelSpecification.getTitleValueEn())
                    .build());

            prodSpecCharValueUses.add(ProdSpecCharValueUse.builder()
                    .id(modelSpecification.getTitleEn())
                    .productSpecCharacteristicValue(productSpecCharacteristicValues)
                    .build());
        }

        List<ProductSpecCharacteristicValue> productSpecCharacteristicFiltersValues = new ArrayList<>();

        for (String filter : model.getFilters()) {
            productSpecCharacteristicFiltersValues.add(ProductSpecCharacteristicValue.builder()
                    .value(filter)
                    .build());
        }
        prodSpecCharValueUses.add(ProdSpecCharValueUse.builder()
                .id(SystemConstant.FILTER_CRITERIA)
                .productSpecCharacteristicValue(productSpecCharacteristicFiltersValues)
                .build());

        prodSpecCharValueUses.add(ProdSpecCharValueUse.builder()
                .id(SystemConstant.CATEGORY)
                .productSpecCharacteristicValue(List.of(ProductSpecCharacteristicValue.builder()
                        .value(model.getClassificationDetails().getCategory())
                        .build()))
                .build());

        prodSpecCharValueUses.add(ProdSpecCharValueUse.builder()
                .id(SystemConstant.SUBCATEGORY)
                .productSpecCharacteristicValue(List.of(ProductSpecCharacteristicValue.builder()
                        .value(model.getClassificationDetails().getSubCategory())
                        .build()))
                .build());

        return prodSpecCharValueUses;
    }


}
