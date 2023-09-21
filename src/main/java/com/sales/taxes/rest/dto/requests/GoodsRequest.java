package com.sales.taxes.rest.dto.requests;

import com.sales.taxes.dao.entities.Category;
import com.sales.taxes.dao.entities.Origin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsRequest {

    @Positive(message = "The quantity must be positive!")
    private final int quantity;
    @NotEmpty(message = "The name must not be empty!")
    private final String goodsName;
    private final Category category;
    private final Origin origin;
    @Pattern(regexp = "^\\d*\\.\\d+|\\d+\\.\\d*$", message = "The number must be a decimal number! Do not forget the dot!")
    @Positive(message = "The price must be positive!")
    private final BigDecimal price;
}
