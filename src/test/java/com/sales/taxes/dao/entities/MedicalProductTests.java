package com.sales.taxes.dao.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.sales.taxes.dao.entities.Origin.IMPORTED;
import static com.sales.taxes.dao.entities.Origin.NOT_IMPORTED;
import static org.assertj.core.api.Assertions.assertThat;

class MedicalProductTests {

    @DisplayName("Valid instance of Medical Product")
    @Test
    void validInstantiationTest() {
        int quantity = 1;
        String goodsName = "packet of headache pills";
        BigDecimal price = BigDecimal.valueOf(0.85);
        Origin origin = NOT_IMPORTED;
        MedicalProduct medicalProduct = new MedicalProduct(quantity, goodsName, price, origin);

        assertThat(medicalProduct).isNotNull();
        assertThat(medicalProduct.getGoodsName()).isEqualTo(goodsName);
        assertThat(medicalProduct.getPrice()).isEqualTo(price);
        assertThat(medicalProduct.getOrigin()).isEqualTo(origin);
    }

    @DisplayName("Import tax of medicalProduct not imported")
    @Test
    void calculateImportTax1Test() {
        int quantity = 1;
        String goodsName = "packet of headache pills";
        BigDecimal price = BigDecimal.valueOf(0.85);
        Origin origin = NOT_IMPORTED;
        MedicalProduct medicalProduct = new MedicalProduct(quantity, goodsName, price, origin);

        BigDecimal salesTaxes = medicalProduct.calculateSalesTaxes();

        assertThat(salesTaxes).isEqualTo("0.00");
    }

    @DisplayName("Import tax of medicalProduct imported")
    @Test
    void calculateImportTax2Test() {
        int quantity = 1;
        String goodsName = "packet of headache pills";
        BigDecimal price = BigDecimal.valueOf(0.85);
        Origin origin = IMPORTED;
        MedicalProduct medicalProduct = new MedicalProduct(quantity, goodsName, price, origin);

        BigDecimal salesTaxes = medicalProduct.calculateSalesTaxes();

        assertThat(salesTaxes).isEqualTo(BigDecimal.valueOf(0.05));
    }

    @DisplayName("Price with tax of medicalProduct not imported")
    @Test
    void calculatePriceWithTax1Test() {
        int quantity = 1;
        String goodsName = "packet of headache pills";
        BigDecimal price = BigDecimal.valueOf(0.85);
        Origin origin = NOT_IMPORTED;
        MedicalProduct medicalProduct = new MedicalProduct(quantity, goodsName, price, origin);

        BigDecimal priceWithTax = medicalProduct.calculatePriceWithTax();

        assertThat(priceWithTax).isEqualTo("0.85");
    }

    @DisplayName("Price with tax of medicalProduct imported")
    @Test
    void calculatePriceWithTax2Test() {
        int quantity = 1;
        String goodsName = "packet of headache pills";
        BigDecimal price = BigDecimal.valueOf(0.85);
        Origin origin = IMPORTED;
        MedicalProduct medicalProduct = new MedicalProduct(quantity, goodsName, price, origin);

        BigDecimal priceWithTax = medicalProduct.calculatePriceWithTax();

        assertThat(priceWithTax).isEqualTo("0.90");
    }
}