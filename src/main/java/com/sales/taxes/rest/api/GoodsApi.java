package com.sales.taxes.rest.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Goods")
@RequestMapping(
        value = "/goods",
        produces = MediaType.APPLICATION_JSON_VALUE + "; charset=utf-8")
public interface GoodsApi {

    @PostMapping
    @Operation(
            summary = "Calculate taxes",
            description = "Calculate taxes")
    ResponseEntity<List<String>> calculate(@Valid @RequestBody List<String> request);
}
