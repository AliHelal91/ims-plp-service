package com.channels.ims.ims_plp_services.consumer;

import com.channels.ims.ims_plp_services.service.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductListener {

    private final ProductsService productsService;


    /**
     *
     * @param payload
     */
    @KafkaListener(topics = {"${kafka.topic.product-updates}"})
    public void saveProductDetails(@Payload String payload, Acknowledgment acknowledgment) {
        try {

            // Save or Update to Products Table
            productsService.saveProductMasterDetails(payload);

            // Commit over Kafka
            acknowledgment.acknowledge();

        } catch (final Exception e) {

            throw e;

        }

    }

}
