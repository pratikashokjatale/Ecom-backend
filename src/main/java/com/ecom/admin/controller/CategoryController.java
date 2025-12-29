package com.ecom.admin.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ecom.admin.dto.CategoryRequest;
import com.ecom.admin.model.Category;
import com.ecom.admin.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/categories")

public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Category createCategory(@RequestBody CategoryRequest request) {
        return categoryService.addCategory(request);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Category> getCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Category getCategory(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

}
