package com.ecom.admin.service;

import java.util.List;

import com.ecom.admin.dto.CategoryRequest;
import com.ecom.admin.model.Category;


public interface CategoryService {

    Category addCategory(CategoryRequest request);
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
}
