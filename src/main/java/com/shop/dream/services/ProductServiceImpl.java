package com.shop.dream.services;

import com.shop.dream.dto.CategoryDTO;
import com.shop.dream.dto.ProductDTO;
import com.shop.dream.models.Category;
import com.shop.dream.models.Product;
import com.shop.dream.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Value("${gcp.bucket}")
    private String http;
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).stream()
                .map(Product::productToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(Product::productToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return productRepository.count();
    }

    @Override
    @Transactional
    public Product save(ProductDTO dto, String imgName) {
        Product product = Product.builder()
                .id(dto.getId())
                .price(dto.getPrice())
                .name(dto.getName())
                .img(http + imgName)
                .description(dto.getDescription())
                .categories(dto.getCategories())
                .build();
        productRepository.save(product);
        return product;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return productRepository.existsByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        var product = productRepository.findById(id);
        return product.map(Product::productToDTO).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Product getReferenceByIdEssence(Long id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    @Transactional
    public boolean update(ProductDTO dto, String img, Category category) {
        if (productRepository.findById(dto.getId()).isPresent()) {
            Product productFromDB = productRepository.findById(dto.getId()).get();
            productFromDB.setName(dto.getName());
            if (img != null && !img.isBlank()) {
                productFromDB.setImg(http + img);
            }
            if (category != null && !productFromDB.getCategories().contains(category)) {
                productFromDB.getCategories().add(category);
            }
            if (dto.getDescription() != null && !dto.getDescription().isBlank()) {
                productFromDB.setDescription(dto.getDescription());
            }
            if (dto.getPrice() != null) {
                productFromDB.setPrice(dto.getPrice());
            }
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryDTO> findCategoriesFromProduct(Long id) {
        return productRepository.findCategoriesFromProduct(id).stream()
                .map(Category::categoryToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> searchProducts(String partName, Pageable pageable) {
        return productRepository.searchProductByName(partName, pageable).stream()
                .map(Product::productToDTO)
                .collect(Collectors.toList());
    }


    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getRandomProducts(Long productId) {
        ProductDTO existingProductDTO = this.findById(productId);
        Set<ProductDTO> all = new HashSet<>();
        if (existingProductDTO.getCategories().size() == 0) {
            return null;
        }
        for (int i = 0; i < existingProductDTO.getCategories().size(); i++) {
            List<ProductDTO> categoryDTOS = categoryService.findProductsByCategoryName(existingProductDTO
                    .getCategories()
                    .get(i).getNameCategory());
            all.addAll(categoryDTOS);
        }
        List<ProductDTO> list = new ArrayList<>(all);
        list.remove(existingProductDTO);
        Random random = new Random();
        List<ProductDTO> result = new ArrayList<>();
        if (list.size() >= 4) {
            while (result.size() < 4) {
                int index = random.nextInt(list.size());
                ProductDTO randomProductDTO = list.get(index);
                if (!result.contains(randomProductDTO) && !randomProductDTO.equals(existingProductDTO)) {
                    result.add(randomProductDTO);
                }
            }
            return result;
        }
        if (list.size() > 1) {
            while (result.size() < list.size() - 1 && result.size() < 4) {
                int index = random.nextInt(list.size());
                ProductDTO randomProductDTO = list.get(index);
                if (!result.contains(randomProductDTO) && !randomProductDTO.equals(existingProductDTO)) {
                    result.add(randomProductDTO);
                }
            }
            return result;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Long countSearchProduct(String name) {
        return productRepository.countSearchProductByName(name);
    }

}
