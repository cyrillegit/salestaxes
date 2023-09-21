package com.sales.taxes.services;

import com.sales.taxes.rest.dto.requests.GoodsRequest;

import java.util.List;

public interface GoodsService {
    List<String> calculate(List<GoodsRequest> goods);
}
