package com.shop.dream.models;

import com.shop.dream.dto.ProductDTO;
import com.shop.dream.dto.ProductMvpDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    @Column(length = 1000)
    private String description;
    private String img;
    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<CartItem> cartItems;
    @ManyToMany
    @JoinTable(
            name = "products_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @ToString.Exclude
    private List<Category> categories;

    public static ProductDTO productToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.id)
                .name(product.name)
                .description(product.description)
                .price(product.price)
                .img(product.img)
                .categories(product.categories)
                .build();
    }

    public static ProductMvpDTO productToMvpDTO(Product product) {
        return ProductMvpDTO.builder()
                .id(product.id)
                .name(product.name)
                .price(product.price)
                .img(product.img)
                .build();
    }
}
