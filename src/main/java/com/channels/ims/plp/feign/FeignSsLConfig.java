package com.channels.ims.plp.feign;

import com.channels.ims.plp.dto.prs.create.response.STCErrorResponse;
import com.channels.ims.plp.exception.ExceptionKey;
import com.channels.ims.plp.exception.ResourceException;
import com.google.gson.Gson;
import feign.Client;
import feign.Response;
import feign.codec.ErrorDecoder;
import feign.hc5.ApacheHttp5Client;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.socket.ConnectionSocketFactory;
import org.apache.hc.client5.http.socket.PlainConnectionSocketFactory;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.http.config.Registry;
import org.apache.hc.core5.http.config.RegistryBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;

import javax.net.ssl.SSLContext;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.util.Locale;

@Configuration
@RequiredArgsConstructor
public class FeignSsLConfig implements ErrorDecoder {

    private final Gson gson;

    @Value("${ssl.key-store}")
    private Resource keyStore;

    @Value("${ssl.key-store-password}")
    private String keyStorePassword;

    @Bean
    public Client feignClient() throws Exception {

        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(keyStore.getInputStream(), keyStorePassword.toCharArray());

        SSLContext sslContext = SSLContexts.custom()
                .loadKeyMaterial(ks, keyStorePassword.toCharArray())
                .build();

        SSLConnectionSocketFactory sslSocketFactory =
                new SSLConnectionSocketFactory(sslContext);

        Registry<ConnectionSocketFactory> socketFactoryRegistry =
                RegistryBuilder.<ConnectionSocketFactory>create()
                        .register("https", sslSocketFactory)
                        .register("http", PlainConnectionSocketFactory.INSTANCE)
                        .build();

        PoolingHttpClientConnectionManager connectionManager =
                new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .build();

        return new ApacheHttp5Client(httpClient);
    }

    @Override
    public Exception decode(String methodKey, Response response) {

        try {

            String body = null;

            if (response.body() != null) {
                body = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            }

            STCErrorResponse errorResponse =
                    gson.fromJson(body, STCErrorResponse.class);

            return new ResourceException(
                    errorResponse.getError().getCode(),
                    HttpStatus.valueOf(Integer.parseInt(errorResponse.getHttpStatusCode())),
                    Locale.getDefault()
            );

        } catch (Exception e) {

            return new ResourceException(
                    ExceptionKey.ERROR_STC_INTEGRATION,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    Locale.getDefault()
            );
        }
    }
}