package com.sales.taxes.services;

import com.sales.taxes.dao.entities.Category;
import com.sales.taxes.dao.entities.Origin;
import com.sales.taxes.rest.dto.requests.GoodsRequest;
import com.sales.taxes.services.impl.GoodsServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class GoodsServiceTests {

    @InjectMocks
    private GoodsServiceImpl goodsService;

    @DisplayName("Calculate all not imported items")
    @Test
    void CalculateNotImportedItemsTest() {
        List<GoodsRequest> goodsRequests = new ArrayList<>();
        goodsRequests.add(new GoodsRequest(1, "book", Category.BOOK, Origin.NOT_IMPORTED, BigDecimal.valueOf(12.49)));
        goodsRequests.add(new GoodsRequest(1, "music CD", Category.OTHERS, Origin.NOT_IMPORTED, BigDecimal.valueOf(14.99)));
        goodsRequests.add(new GoodsRequest(1, "chocolate bar", Category.FOOD, Origin.NOT_IMPORTED, BigDecimal.valueOf(0.85)));

        List<String> result = goodsService.calculate(goodsRequests);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(4);
        assertThat(result.get(0)).isEqualTo("1 book: 12.49");
        assertThat(result.get(1)).isEqualTo("1 music CD: 16.49");
        assertThat(result.get(2)).isEqualTo("1 chocolate bar: 0.85");
        assertThat(result.get(3)).isEqualTo("Sales Taxes: 1.50  Total: 29.83");
    }

    @DisplayName("Calculate all imported items")
    @Test
    void CalculateImportedItemsTest() {
        List<GoodsRequest> goodsRequests = new ArrayList<>();
        goodsRequests.add(new GoodsRequest(1, "imported box of chocolates", Category.FOOD, Origin.IMPORTED, BigDecimal.valueOf(10.00)));
        goodsRequests.add(new GoodsRequest(1, "imported bottle of perfume", Category.OTHERS, Origin.IMPORTED, BigDecimal.valueOf(47.50)));

        List<String> result = goodsService.calculate(goodsRequests);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0)).isEqualTo("1 imported bottle of perfume: 54.65");
        assertThat(result.get(1)).isEqualTo("1 imported box of chocolates: 10.50");
        assertThat(result.get(2)).isEqualTo("Sales Taxes: 7.65  Total: 65.15");
    }

    @DisplayName("Calculate all mixed items")
    @Test
    void CalculateMixedItemsTest() {
        List<GoodsRequest> goodsRequests = new ArrayList<>();
        goodsRequests.add(new GoodsRequest(1, "imported bottle of perfume", Category.OTHERS, Origin.IMPORTED, BigDecimal.valueOf(27.99)));
        goodsRequests.add(new GoodsRequest(1, "bottle of perfume", Category.OTHERS, Origin.NOT_IMPORTED, BigDecimal.valueOf(18.99)));
        goodsRequests.add(new GoodsRequest(1, "packet of headache pills", Category.MEDICAL_PRODUCT, Origin.NOT_IMPORTED, BigDecimal.valueOf(9.75)));
        goodsRequests.add(new GoodsRequest(1, "box of imported chocolates", Category.FOOD, Origin.IMPORTED, BigDecimal.valueOf(11.25)));

        List<String> result = goodsService.calculate(goodsRequests);

        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(5);
        assertThat(result.get(0)).isEqualTo("1 imported bottle of perfume: 32.19");
        assertThat(result.get(1)).isEqualTo("1 bottle of perfume: 20.89");
        assertThat(result.get(2)).isEqualTo("1 box of imported chocolates: 11.85");
        assertThat(result.get(3)).isEqualTo("1 packet of headache pills: 9.75");
        assertThat(result.get(4)).isEqualTo("Sales Taxes: 6.70  Total: 74.68");
    }
}

