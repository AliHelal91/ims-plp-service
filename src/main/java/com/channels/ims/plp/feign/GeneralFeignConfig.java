package com.channels.ims.plp.feign;

import com.channels.ims.plp.constant.SystemConstant;
import com.channels.ims.plp.dto.prs.create.response.STCErrorResponse;
import com.channels.ims.plp.exception.ExceptionKey;
import com.channels.ims.plp.exception.ResourceException;
import com.google.gson.Gson;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

@Configuration
@RequiredArgsConstructor
public class GeneralFeignConfig implements ErrorDecoder, RequestInterceptor {

    private final Gson gson;

    @Override
    public void apply(final RequestTemplate requestTemplate) {
        requestTemplate.header(SystemConstant.COUNTRY_CODE, "sa");
    }


    @Override
    public Exception decode(String methodKey, Response response) {

        return new ResourceException(
                    response.reason(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    Locale.getDefault()
            );
    }
}
