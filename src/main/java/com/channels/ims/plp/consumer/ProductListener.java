package com.channels.ims.plp.consumer;

import com.channels.ims.plp.dto.kafka.ddl.KafkaDeadLetterDto;
import com.channels.ims.plp.service.KafkaPublisher;
import com.channels.ims.plp.service.ProductsService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductListener {

    private final ProductsService productsService;
    private final KafkaPublisher kafkaPublisher;
    private final Gson gson;


    @Value("${kafka.topic.product-updates-ddl}")
    private String productTopicDDL;


    /**
     * Sync Product from ims-product-service
     *
     * @param payload ProductDetails
     */
    @KafkaListener(topics = {"${kafka.topic.product-updates}"})
    public void saveProductDetails(@Payload String payload, Acknowledgment acknowledgment) {
        try {

            // Save or Update to Products Table
            productsService.saveProductMasterDetails(payload);

            // Commit over Kafka
            acknowledgment.acknowledge();

        } catch (final Exception e) {

            String message = gson.toJson(KafkaDeadLetterDto.builder()
                    .payload(payload)
                    .errorMessage(e.getMessage())
                    .build());

            kafkaPublisher.send(productTopicDDL, message);

        }

    }

}
