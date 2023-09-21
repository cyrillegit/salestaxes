package com.sales.taxes.dao.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface Goods {

    double BASIC_SALES_TAX = 0.1;
    double IMPORT_TAX = 0.05;
    BigDecimal INCREMENT = BigDecimal.valueOf(0.05);

    int getQuantity();

    String getGoodsName();

    BigDecimal getPrice();

    Origin getOrigin();

    String getCategory();

    BigDecimal calculateSalesTaxes();

    BigDecimal calculatePriceWithTax();

    default BigDecimal roundUpPrice(final BigDecimal price) {
        return price
                .divide(INCREMENT, 0, RoundingMode.UP)
                .multiply(INCREMENT);
    }
}
