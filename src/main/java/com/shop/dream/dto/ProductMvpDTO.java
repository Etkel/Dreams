package com.shop.dream.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductMvpDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private String img;
}
