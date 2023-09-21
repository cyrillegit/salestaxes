package com.sales.taxes.dao.entities;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

import static com.sales.taxes.dao.entities.Origin.IMPORTED;

@Getter
@Setter
public class MedicalProduct extends AbstractGoods implements Goods {

    public MedicalProduct(int quantity, String goodsName, BigDecimal price, Origin origin) {
        super(quantity, goodsName, price, origin);
    }

    @Override
    public String getCategory() {
        return this.getClass().getSimpleName();
    }

    @Override
    public BigDecimal calculateSalesTaxes() {
        return this.roundUpPrice(this.calculateImportTax());
    }

    @Override
    public BigDecimal calculatePriceWithTax() {
        return this.price.add(this.calculateSalesTaxes());
    }

    private BigDecimal calculateImportTax() {
        BigDecimal tax = BigDecimal.ZERO;
        if (this.origin.equals(IMPORTED)) {
            tax = this.price.multiply(BigDecimal.valueOf(IMPORT_TAX));
        }
        return tax;
    }

    @Override
    public String toString() {
        return quantity + " " + goodsName + ": " + calculatePriceWithTax();
    }
}
