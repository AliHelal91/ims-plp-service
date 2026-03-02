package com.channels.ims.ims_plp_services.controller;

import com.channels.ims.ims_plp_services.dto.temptable.create.GenerateTempTableRequest;
import com.channels.ims.ims_plp_services.dto.temptable.create.GenerateTempTableResponse;
import com.channels.ims.ims_plp_services.dto.temptable.fetch.single.SingleTempTableResponse;
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


    @PostMapping
    public ResponseEntity<GenerateTempTableResponse> create(
            @RequestBody @Valid GenerateTempTableRequest tempTableRequest,
            final HttpServletRequest request) {

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
