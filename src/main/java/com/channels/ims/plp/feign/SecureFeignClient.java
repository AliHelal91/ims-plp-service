package com.channels.ims.plp.feign;

import com.channels.ims.plp.dto.prs.create.request.PrsCreateRequest;
import com.channels.ims.plp.dto.prs.create.response.PrsCreateResponse;
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
    PrsCreateResponse createPRS(@RequestBody PrsCreateRequest  prsCreateRequest);
}
