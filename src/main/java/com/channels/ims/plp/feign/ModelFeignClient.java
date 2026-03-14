package com.channels.ims.plp.feign;

import com.channels.ims.plp.dto.model.ModelDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "modelClient",
        url = "${product.service.url}",
        configuration = GeneralFeignConfig.class
)
public interface ModelFeignClient {

    @GetMapping("/models/getAll")
    ModelDetailsResponse getModelDetails(@RequestParam(value = "id") Integer modelId);
}
