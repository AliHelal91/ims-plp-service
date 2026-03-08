package com.channels.ims.plp.controller;

import com.channels.ims.plp.dto.temptable.create.GenerateTempTableRequest;
import com.channels.ims.plp.dto.temptable.create.GenerateTempTableResponse;
import com.channels.ims.plp.dto.temptable.fetch.single.SingleTempTableResponse;
import com.channels.ims.plp.feign.SecureFeignClient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("v1/temp")
@RequiredArgsConstructor
public class TempController {

    private final SecureFeignClient secureFeignClient;

    @PostMapping
    public ResponseEntity<GenerateTempTableResponse> create(
            @RequestBody @Valid GenerateTempTableRequest tempTableRequest,
            final HttpServletRequest request) {

        System.out.println(secureFeignClient.getSecureData("{\n" +
                "    \"imsProjectId\": \"IMS_PRJ_009\",\n" +
                "    \"manualTesting\": true,\n" +
                "    \"physicalResourceSpecification\": [\n" +
                "        {\n" +
                "            \"productSpecification\": {\n" +
                "                \"name\": \"tablets\",\n" +
                "                \"id\": \"fc2c8577-3c13-4d69-ad04-33fc8ee3518b\"\n" +
                "            },\n" +
                "            \"prodSpecCharValueUse\": [\n" +
                "                {\n" +
                "                    \"id\": \"imsPrsId\",\n" +
                "                    \"productSpecCharacteristicValue\": [\n" +
                "                        {\n" +
                "                            \"value\": \"IMSPRS9843576\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"whitelistFlag\",\n" +
                "                    \"productSpecCharacteristicValue\": [\n" +
                "                        {\n" +
                "                            \"value\": false\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"Color\",\n" +
                "                    \"productSpecCharacteristicValue\": [\n" +
                "                        {\n" +
                "                            \"value\": \"Green\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"resourceType\",\n" +
                "                    \"productSpecCharacteristicValue\": [\n" +
                "                        {\n" +
                "                            \"value\": \"MOBILE_DEVICE\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"actionCode\",\n" +
                "                    \"productSpecCharacteristicValue\": [\n" +
                "                        {\n" +
                "                            \"value\": \"N/A\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"filterCriteria\",\n" +
                "                    \"productSpecCharacteristicValue\": [\n" +
                "                        {\n" +
                "                            \"value\": \"colors\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"value\": \"storages\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"value\": \"bundle\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"value\": \"rams\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"STC_protect\",\n" +
                "                    \"productSpecCharacteristicValue\": [\n" +
                "                        {\n" +
                "                            \"value\": \"false\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"simEnabled\",\n" +
                "                    \"productSpecCharacteristicValue\": [\n" +
                "                        {\n" +
                "                            \"value\": \"false\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"productCategory\",\n" +
                "                    \"productSpecCharacteristicValue\": [\n" +
                "                        {\n" +
                "                            \"value\": \"tablets\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"productSubCategory\",\n" +
                "                    \"productSpecCharacteristicValue\": [\n" +
                "                        {\n" +
                "                            \"value\": \"apple\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"productSubSubCategory\",\n" +
                "                    \"productSpecCharacteristicValue\": [\n" +
                "                        {\n" +
                "                            \"value\": \"Magic Mouze\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                {\n" +
                "                    \"id\": \"productSubSubSubCategory\",\n" +
                "                    \"productSpecCharacteristicValue\": [\n" +
                "                        {\n" +
                "                            \"value\": \"NIOT\",\n" +
                "                            \"label\": \"Not IoT\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                }\n" +
                "            ],\n" +
                "            \"validFor\": {\n" +
                "                \"startDateTime\": \"2026-04-28T05:00:00+01:00\"\n" +
                "            },\n" +
                "            \"name\": \"2000 inch MacBook Air M4 (2025)\",\n" +
                "            \"title\": [\n" +
                "                {\n" +
                "                    \"text\": \"2000 inch MacBook Air M4 (2025)\",\n" +
                "                    \"language\": \"en-xx\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"text\": \"2000 inch MacBook Air M4 (2025)\",\n" +
                "                    \"language\": \"ar-xx\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"productType\": \"SERVICE\",\n" +
                "            \"availableFor\": {\n" +
                "                \"startDateTime\": \"2026-04-28T05:00:00+01:00\"\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}"));
        // Return Response
        return new ResponseEntity<>(GenerateTempTableResponse.builder().build(), HttpStatus.CREATED);
    }

    /**
     * Find Temp Table By Code Example
     *
     * @param code    Attribute Code
     * @param request HttpServletRequest
     * @return SingleTempTableResponse
     */
    @GetMapping(value = "/{code}")
    public ResponseEntity<SingleTempTableResponse> findByCode(
            @PathVariable final String code,
            final HttpServletRequest request) {

        // Return Response
        return new ResponseEntity<>(SingleTempTableResponse.builder().build(), HttpStatus.OK);


    }
}
