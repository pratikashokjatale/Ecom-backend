package com.ecom.admin.service.impl;



import org.springframework.stereotype.Service;

import com.ecom.admin.dto.CategoryRequest;
import com.ecom.admin.model.Category;
import com.ecom.admin.model.SubCategory;
import com.ecom.admin.repository.CategoryRepository;
import com.ecom.admin.repository.SubCategoryRepository;
import com.ecom.admin.service.CategoryService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository,
                               SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public Category addCategory(CategoryRequest request) {

        Category category = new Category();
        category.setName(request.getName());
        category.setImageUrl(request.getImageUrl());

        Category savedCategory = categoryRepository.save(category);

        List<SubCategory> subCategoryList = new ArrayList<>();

        for (String sc : request.getSubCategories()) {
            SubCategory sub = new SubCategory();
            sub.setName(sc);
            sub.setCategory(savedCategory);
            subCategoryRepository.save(sub);
            subCategoryList.add(sub);
        }

        savedCategory.setSubCategories(subCategoryList);

        return categoryRepository.save(savedCategory);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }
    
}

