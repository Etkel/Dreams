package com.shop.dream.dto;

import com.shop.dream.models.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    private Long id;
    private String nameCategory;
    private String imageNameCategory;
    private String description;
    private List<Product> productList;
}
