package com.shop.dream.contollers;

import com.shop.dream.dto.CategoryDTO;
import com.shop.dream.dto.ProductDTO;
import com.shop.dream.services.CategoryService;
import com.shop.dream.services.OrderService;
import com.shop.dream.services.PersonaService;
import com.shop.dream.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import static com.shop.dream.configs.GoogleCloudConfig.uploadFile;
import static com.shop.dream.contollers.CategoryController.getMultipartFileName;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final PersonaService personaService;
    private final OrderService orderService;

    @GetMapping("admin/product-add")
    public String productForm(Model model) {
        List<CategoryDTO> categoryDTOS = categoryService.findAll();
        List<ProductDTO> productDTOS = productService.findAll();
        model.addAttribute("categories", categoryDTOS);
        model.addAttribute("products", productDTOS);
        model.addAttribute("product", new ProductDTO());
        model.addAttribute("categoryId", null);
        return "product-add";
    }

    @PostMapping("admin/product-add")
    public String saveProduct(ProductDTO dto,
                              MultipartFile multipartFile,
                              @RequestParam Long categoryId) {
        if (multipartFile.isEmpty()) {
            return "redirect:/admin/product-add?miss";
        }
        String fileName = getMultipartFileName(multipartFile);
        try {
            uploadFile(multipartFile, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (productService.existsByName(dto.getName())) {
            return "redirect:/admin/product-add?error";
        }
        var product = productService.save(dto, fileName);
        categoryService.addProduct(categoryId, product);
        return "redirect:/admin/product-add?success";
    }

    @PostMapping("admin/delete-product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("admin/update-product/{productId}")
    public String updateProduct(@PathVariable Long productId, Model model) {
        List<CategoryDTO> categoryDTOS = categoryService.findAll();
        model.addAttribute("categories", categoryDTOS);
        model.addAttribute("product", productService.findById(productId));
        model.addAttribute("categoryId", null);
        model.addAttribute("categoriesFromProduct", productService.findCategoriesFromProduct(productId));
        model.addAttribute("categoryDelId", null);
        return "product-upd";
    }

    @PostMapping("admin/update-product/{productId}")
    public String updateCategoryDo(@PathVariable Long productId,
                                   ProductDTO productDTO,
                                   @RequestParam(required = false, name = "multipartFile") MultipartFile file,
                                   @RequestParam(required = false) Long categoryId,
                                   @RequestParam(required = false) Long categoryDelId) {
        String fileName = getMultipartFileName(file);
        if (!file.isEmpty()) {
            try {
                uploadFile(file, fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (productService.update(productDTO, fileName, categoryService.findByIdEssence(categoryId))) {
            ProductDTO dto = productService.findById(productId);
            categoryService.delProduct(categoryDelId, dto);
            return "redirect:/admin/update-product/" + productId + "?success";
        }
        return "redirect:/admin/update-product/" + productId;
    }


    @GetMapping("/product/{productId}")
    public String productShow(@PathVariable Long productId, Model model, Principal principal) {
        model.addAttribute("product", productService.findById(productId));
        //Related products
        model.addAttribute("relatedProducts", productService.getRandomProducts(productId));
        //Navigation
        List<CategoryDTO> categoryDTOS = categoryService.findAll();
        model.addAttribute("categoryNav", categoryDTOS);
        if (principal != null){
            model.addAttribute("cartSize", personaService.findCartSizeByEmail(principal.getName()));
        }
        model.addAttribute("uncheckedOrders", orderService.countUncheckedOrders());
        model.addAttribute("principal", principal);
        return "product";
    }

}
