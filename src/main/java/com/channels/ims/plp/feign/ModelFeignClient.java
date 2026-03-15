package com.channels.ims.plp.feign;

import com.channels.ims.plp.dto.model.ModelDetailsPageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "modelClient",
        url = "${product.service.url}",
        configuration = GeneralFeignConfig.class
)
public interface ModelFeignClient {

    @GetMapping("/models/getAll")
    ResponseEntity<ModelDetailsPageResponse> getModelDetails(@RequestParam(value = "id") String modelId,
                                                             @RequestParam(value = "pageNumber") String pageNumber,
                                                             @RequestParam(value = "pageLimit") String pageLimit,
                                                             @RequestParam(value = "isAttributeValue") String isAttributeValue);
}
