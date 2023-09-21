package com.sales.taxes.rest.controllers;

import com.sales.taxes.helpers.GoodsHelper;
import com.sales.taxes.rest.api.GoodsApi;
import com.sales.taxes.services.GoodsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class GoodsController implements GoodsApi {

    private final GoodsService goodsService;

    @Override
    public ResponseEntity<List<String>> calculate(List<String> request) {
        return ResponseEntity.ok(goodsService.calculate(GoodsHelper.extractAllGoods(request)));
    }
}
