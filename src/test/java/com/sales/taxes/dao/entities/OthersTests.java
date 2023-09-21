package com.sales.taxes.dao.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.sales.taxes.dao.entities.Origin.IMPORTED;
import static com.sales.taxes.dao.entities.Origin.NOT_IMPORTED;
import static org.assertj.core.api.Assertions.assertThat;

class OthersTests {

    @DisplayName("Valid instance ofOthers ")
    @Test
    void validInstantiationTest() {
        int quantity = 1;
        String goodsName = "music CD";
        BigDecimal price = BigDecimal.valueOf(14.99);
        Origin origin = NOT_IMPORTED;
        Food food = new Food(quantity, goodsName, price, origin);

        assertThat(food).isNotNull();
        assertThat(food.getGoodsName()).isEqualTo(goodsName);
        assertThat(food.getPrice()).isEqualTo(price);
        assertThat(food.getOrigin()).isEqualTo(origin);
    }

    @DisplayName("Import tax of Others not imported")
    @Test
    void calculateSalesTaxe1Test() {
        int quantity = 1;
        String goodsName = "music CD";
        BigDecimal price = BigDecimal.valueOf(14.99);
        Origin origin = NOT_IMPORTED;
        Others others = new Others(quantity, goodsName, price, origin);

        BigDecimal salesTaxes = others.calculateSalesTaxes();

        assertThat(salesTaxes).isEqualTo("1.50");
    }

    @DisplayName("Import tax of Others imported")
    @Test
    void calculateSalesTaxe2() {
        int quantity = 1;
        String goodsName = "music CD";
        BigDecimal price = BigDecimal.valueOf(14.99);
        Origin origin = IMPORTED;
        MedicalProduct medicalProduct = new MedicalProduct(quantity, goodsName, price, origin);

        BigDecimal salesTaxes = medicalProduct.calculateSalesTaxes();

        assertThat(salesTaxes).isEqualTo("0.75");
    }

    @DisplayName("Import tax of Others not imported")
    @Test
    void calculatePriceWithTax1Test() {
        int quantity = 1;
        String goodsName = "music CD";
        BigDecimal price = BigDecimal.valueOf(14.99);
        Origin origin = NOT_IMPORTED;
        Others others = new Others(quantity, goodsName, price, origin);

        BigDecimal priceWithTax = others.calculatePriceWithTax();

        assertThat(priceWithTax).isEqualTo("16.49");
    }

    @DisplayName("Import tax of Others imported")
    @Test
    void calculatePriceWithTax2Test() {
        int quantity = 1;
        String goodsName = "music CD";
        BigDecimal price = BigDecimal.valueOf(14.99);
        Origin origin = IMPORTED;
        MedicalProduct medicalProduct = new MedicalProduct(quantity, goodsName, price, origin);

        BigDecimal priceWithTax = medicalProduct.calculatePriceWithTax();

        assertThat(priceWithTax).isEqualTo("15.74");
    }
}