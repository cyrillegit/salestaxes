package com.sales.taxes.dao.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.sales.taxes.dao.entities.Origin.IMPORTED;
import static com.sales.taxes.dao.entities.Origin.NOT_IMPORTED;
import static org.assertj.core.api.Assertions.assertThat;

class FoodTests {

    @DisplayName("Valid instance of food")
    @Test
    void validInstantiationTest() {
        int quantity = 1;
        String goodsName = "chocolate bar";
        BigDecimal price = BigDecimal.valueOf(0.85);
        Origin origin = NOT_IMPORTED;
        Food food = new Food(quantity, goodsName, price, origin);

        assertThat(food).isNotNull();
        assertThat(food.getGoodsName()).isEqualTo(goodsName);
        assertThat(food.getPrice()).isEqualTo(price);
        assertThat(food.getOrigin()).isEqualTo(origin);
    }

    @DisplayName("Import tax for food not imported")
    @Test
    void calculateImportTax1Test() {
        int quantity = 1;
        String goodsName = "chocolate bar";
        BigDecimal price = BigDecimal.valueOf(0.85);
        Origin origin = NOT_IMPORTED;
        Food food = new Food(quantity, goodsName, price, origin);

        BigDecimal salesTaxes = food.calculateSalesTaxes();

        assertThat(salesTaxes).isEqualTo("0.00");
    }

    @DisplayName("Import tax for food imported")
    @Test
    void calculateImportTax2Test() {
        int quantity = 1;
        String goodsName = "chocolate bar";
        BigDecimal price = BigDecimal.valueOf(0.85);
        Origin origin = IMPORTED;
        Food food = new Food(quantity, goodsName, price, origin);

        BigDecimal salesTaxes = food.calculateSalesTaxes();

        assertThat(salesTaxes).isEqualTo(BigDecimal.valueOf(0.05));
    }

    @DisplayName("Price with tax for food not imported")
    @Test
    void calculatePriceWithTax1Test() {
        int quantity = 1;
        String goodsName = "chocolate bar";
        BigDecimal price = BigDecimal.valueOf(0.85);
        Origin origin = NOT_IMPORTED;
        Food food = new Food(quantity, goodsName, price, origin);

        BigDecimal priceWithTax = food.calculatePriceWithTax();

        assertThat(priceWithTax).isEqualTo("0.85");
    }

    @DisplayName("Price with tax for food imported")
    @Test
    void calculatePriceWithTax2Test() {
        int quantity = 1;
        String goodsName = "chocolate bar";
        BigDecimal price = BigDecimal.valueOf(0.85);
        Origin origin = IMPORTED;
        Food food = new Food(quantity, goodsName, price, origin);

        BigDecimal priceWithTax = food.calculatePriceWithTax();


        assertThat(priceWithTax).isEqualTo("0.90");
    }
}