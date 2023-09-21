package com.sales.taxes.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public abstract class AbstractGoods {
    protected int quantity;
    protected String goodsName;
    protected BigDecimal price;
    protected Origin origin;
}
