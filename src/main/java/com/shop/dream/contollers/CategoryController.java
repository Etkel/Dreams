package com.shop.dream.contollers;

import com.shop.dream.dto.CategoryDTO;
import com.shop.dream.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.shop.dream.configs.GoogleCloudConfig.uploadFile;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("admin/category-add")
    public String categoryForm(Model model) {
        List<CategoryDTO> categoryDTOS = categoryService.findAll();
        model.addAttribute("categories", categoryDTOS);
        model.addAttribute("category", new CategoryDTO());
        return "category-add";
    }

    @PostMapping("admin/category-add")
    public String saveCategory(CategoryDTO dto, MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return "redirect:/admin/category-add?miss";
        }
        String fileName = getMultipartFileName(multipartFile);
        try {
            uploadFile(multipartFile, fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (categoryService.existsByName(dto.getNameCategory())) {
            return "redirect:/admin/category-add?error";
        }
        if (categoryService.save(dto, fileName)) {
            return "redirect:/admin/category-add?success";
        }
        return "category-add";
    }

    @PostMapping("admin/delete-category/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("admin/update-category/{categoryId}")
    public String updateCategory(@PathVariable Long categoryId, Model model) {
        model.addAttribute("category", categoryService.findById(categoryId));
        return "category-upd";
    }

    @PostMapping("admin/update-category/{categoryId}")
    public String updateCategoryDo(@PathVariable Long categoryId,
                                   CategoryDTO categoryDTO,
                                   @RequestParam(required = false) MultipartFile multipartFile) {
        String fileName = getMultipartFileName(multipartFile);
        if (!multipartFile.isEmpty()) {
            try {
                uploadFile(multipartFile, fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (categoryService.update(categoryDTO, fileName)) {
            return "redirect:/admin/category-add?success";
        }
        return "category-upd";
    }

    public static String getMultipartFileName(MultipartFile file) {
        String fileName = "";
        if (!file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            fileName = originalFilename + "_" + UUID.randomUUID() + extension;
        }
        return fileName;
    }

}
