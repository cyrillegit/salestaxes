package com.sales.taxes.dao.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.sales.taxes.dao.entities.Origin.IMPORTED;
import static com.sales.taxes.dao.entities.Origin.NOT_IMPORTED;
import static org.assertj.core.api.Assertions.assertThat;

public class BookTests {

    @DisplayName("Valid instance of book")
    @Test
    void validInstanceTest() {
        int quantity = 1;
        String goodsName = "Chocolate";
        BigDecimal price = BigDecimal.valueOf(12.49);
        Origin origin = NOT_IMPORTED;
        Book book = new Book(quantity, goodsName, price, origin);

        assertThat(book).isNotNull();
        assertThat(book.getQuantity()).isEqualTo(quantity);
        assertThat(book.getGoodsName()).isEqualTo(goodsName);
        assertThat(book.getPrice()).isEqualTo(price);
        assertThat(book.getOrigin()).isEqualTo(origin);
    }

    @DisplayName("Import tax for book not imported")
    @Test
    void calculateImportTaxForNotImportedBookTest() {
        int quantity = 1;
        String goodsName = "Chocolate";
        BigDecimal price = BigDecimal.valueOf(12.49);
        Origin origin = NOT_IMPORTED;
        Book book = new Book(quantity, goodsName, price, origin);

        BigDecimal salesTaxes = book.calculateSalesTaxes();

        assertThat(salesTaxes).isEqualTo("0.00");
    }

    @DisplayName("Import tax for book imported")
    @Test
    void calculateImportTaxForImportedBookTest() {
        int quantity = 1;
        String goodsName = "Chocolate";
        BigDecimal price = BigDecimal.valueOf(12.49);
        Origin origin = IMPORTED;
        Book book = new Book(quantity, goodsName, price, origin);

        BigDecimal salesTaxes = book.calculateSalesTaxes();

        assertThat(salesTaxes).isEqualTo(BigDecimal.valueOf(0.65));
    }

    @DisplayName("Price with tax for book not imported")
    @Test
    void calculatePriceWithTaxForNotImportedBookTest() {
        int quantity = 1;
        String goodsName = "Chocolate";
        BigDecimal price = BigDecimal.valueOf(12.49);
        Origin origin = NOT_IMPORTED;
        Book book = new Book(quantity, goodsName, price, origin);

        BigDecimal priceWithTax = book.calculatePriceWithTax();

        assertThat(priceWithTax).isEqualTo("12.49");
    }

    @DisplayName("Price with tax for book imported")
    @Test
    void calculatePriceWithTaxForImportedBookTest() {
        int quantity = 1;
        String goodsName = "Chocolate";
        BigDecimal price = BigDecimal.valueOf(12.49);
        Origin origin = IMPORTED;
        Book book = new Book(quantity, goodsName, price, origin);

        BigDecimal priceWithTax = book.calculatePriceWithTax();

        assertThat(priceWithTax).isEqualTo(BigDecimal.valueOf(13.14));
    }
}
