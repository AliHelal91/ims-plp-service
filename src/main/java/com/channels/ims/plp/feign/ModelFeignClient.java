package com.channels.ims.plp.feign;

import com.channels.ims.plp.dto.model.ModelDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "modelClient",
        url = "https://sandbox.api.stc.com.sa:9512/s-tmf/productCatalogManagement/consumer/v1/bulk/prs",
        configuration = FeignSsLConfig.class
)
public interface ModelFeignClient {

    @GetMapping
    ModelDetailsResponse getModelDetails(@RequestParam(value = "modelId") Integer modelId);
}
