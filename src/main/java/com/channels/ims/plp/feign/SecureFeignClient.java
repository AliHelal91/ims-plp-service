package com.channels.ims.plp.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "secureClient",
        url = "https://sandbox.api.stc.com.sa:9512/s-tmf/productCatalogManagement/consumer/v1/bulk/prs",
        configuration = FeignSsLConfig.class
)
public interface SecureFeignClient {

    @PostMapping
    String getSecureData(@RequestBody String body);
}
