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

//        try {
//
//            String body = null;
//
//            if (response.body() != null) {
//                body = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
//            }
//
//            STCErrorResponse errorResponse =
//                    gson.fromJson(body, STCErrorResponse.class);
//
//            return new ResourceException(
//                    errorResponse.getError().getCode(),
//                    HttpStatus.valueOf(Integer.parseInt(errorResponse.getHttpStatusCode())),
//                    Locale.getDefault()
//            );
//
//        } catch (Exception e) {
//
//            return new ResourceException(
//                    ExceptionKey.ERROR_STC_INTEGRATION,
//                    HttpStatus.INTERNAL_SERVER_ERROR,
//                    Locale.getDefault()
//            );
//        }

        return new ResourceException(
                    response.reason(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    Locale.getDefault()
            );
    }
}
