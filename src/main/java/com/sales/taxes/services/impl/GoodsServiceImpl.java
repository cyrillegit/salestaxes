package com.sales.taxes.services.impl;

import com.sales.taxes.dao.entities.Book;
import com.sales.taxes.dao.entities.Food;
import com.sales.taxes.dao.entities.MedicalProduct;
import com.sales.taxes.dao.entities.Others;
import com.sales.taxes.rest.dto.requests.GoodsRequest;
import com.sales.taxes.services.GoodsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Override
    public List<String> calculate(List<GoodsRequest> goods) {
        List<Book> books = new ArrayList<>();
        List<Others> others = new ArrayList<>();
        List<Food> foods = new ArrayList<>();
        List<MedicalProduct> medicalProducts = new ArrayList<>();
        List<String> results = new ArrayList<>();
        BigDecimal salesTaxes = BigDecimal.ZERO;
        BigDecimal total = BigDecimal.ZERO;

        for (GoodsRequest item : goods) {
            switch (item.getCategory()) {
                case BOOK -> {
                    Book book = new Book(item.getQuantity(), item.getGoodsName(), item.getPrice(), item.getOrigin());
                    salesTaxes = salesTaxes.add(book.calculateSalesTaxes());
                    total = total.add(book.calculatePriceWithTax());
                    books.add(book);
                }
                case FOOD -> {
                    Food food = new Food(item.getQuantity(), item.getGoodsName(), item.getPrice(), item.getOrigin());
                    salesTaxes = salesTaxes.add(food.calculateSalesTaxes());
                    total = total.add(food.calculatePriceWithTax());
                    foods.add(food);
                }
                case MEDICAL_PRODUCT -> {
                    MedicalProduct med = new MedicalProduct(item.getQuantity(), item.getGoodsName(), item.getPrice(), item.getOrigin());
                    salesTaxes = salesTaxes.add(med.calculateSalesTaxes());
                    total = total.add(med.calculatePriceWithTax());
                    medicalProducts.add(med);
                }
                case OTHERS -> {
                    Others other = new Others(item.getQuantity(), item.getGoodsName(), item.getPrice(), item.getOrigin());
                    salesTaxes = salesTaxes.add(other.calculateSalesTaxes());
                    total = total.add(other.calculatePriceWithTax());
                    others.add(other);
                }
            }
        }

        books.forEach(book -> results.add(book.toString()));
        others.forEach(other -> results.add(other.toString()));
        foods.forEach(food -> results.add(food.toString()));
        medicalProducts.forEach(med -> results.add(med.toString()));
        results.add("Sales Taxes: " + salesTaxes + "  Total: " + total);
        return results;
    }
}
