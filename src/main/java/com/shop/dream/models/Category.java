package com.shop.dream.models;

import com.shop.dream.dto.CategoryDTO;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCategory;
    private String imageNameCategory;
    @Column(length = 650)
    private String description;
    @ManyToMany(mappedBy = "categories")
    @ToString.Exclude
    private List<Product> productList;

    public static CategoryDTO categoryToDTO(Category category) {
        return CategoryDTO.builder()
                .id(category.id)
                .nameCategory(category.nameCategory)
                .description(category.description)
                .imageNameCategory(category.imageNameCategory)
                .productList(category.productList)
                .build();
    }
}
