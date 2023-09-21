package com.sales.taxes.helpers;

import com.sales.taxes.dao.entities.Category;
import com.sales.taxes.dao.entities.Origin;
import com.sales.taxes.rest.dto.requests.GoodsRequest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoodsHelper {

    public static List<GoodsRequest> extractAllGoods(final List<String> listGoods) {
        List<GoodsRequest> goods = new ArrayList<>();
        if (listGoods != null && !listGoods.isEmpty()) {
            listGoods.forEach(s -> goods.add(extractGoods(s)));
        }
        return goods;
    }

    public static GoodsRequest extractGoods(final String inputString) {
        final List<String> strings = new ArrayList<>(Arrays.asList(inputString.split(" ")));

        Origin origin;
        if (strings.contains("imported")) {
            origin = Origin.IMPORTED;
        } else {
            origin = Origin.NOT_IMPORTED;
        }

        final BigDecimal price = BigDecimal.valueOf(Double.parseDouble(strings.get(strings.size() - 1)));

        strings.remove(strings.size() - 1);
        strings.remove(strings.size() - 1);

        final int quantity = Integer.parseInt(strings.remove(0));

        final String goodsName = String.join(" ", strings);

        final Category category = calculateCategory(goodsName);

        return new GoodsRequest(quantity, goodsName, category, origin, price);
    }

    private static Category calculateCategory(final String goodsName) {
        Category category;
        if (goodsName.contains("book")) {
            category = Category.BOOK;
        } else if (goodsName.contains("chocolate")) {
            category = Category.FOOD;
        } else if (goodsName.contains("headache")) {
            category = Category.MEDICAL_PRODUCT;
        } else {
            category = Category.OTHERS;
        }

        return category;
    }
}
