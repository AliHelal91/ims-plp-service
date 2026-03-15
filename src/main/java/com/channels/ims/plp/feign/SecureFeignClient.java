package com.channels.ims.plp.feign;

import com.channels.ims.plp.dto.po.stc.create.ProductOfferingRequestDTO;
import com.channels.ims.plp.dto.po.stc.create.ProductOfferingResponseDTO;
import com.channels.ims.plp.dto.prs.create.request.PrsCreateRequest;
import com.channels.ims.plp.dto.prs.create.response.PrsCreateResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "secureClient",
        url = "${stc.sync.url}",
        configuration = FeignSsLConfig.class
)
public interface SecureFeignClient {

    @PostMapping("/bulk/prs")
    PrsCreateResponse createPRS(@RequestBody PrsCreateRequest prsCreateRequest);

    @PostMapping("/bulk/po")
    ProductOfferingResponseDTO createPO(@RequestBody ProductOfferingRequestDTO prsCreateRequest);
}
