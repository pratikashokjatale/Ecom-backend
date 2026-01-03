package com.ecom.vendor.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.vendor.entity.CategoryRef;
import com.ecom.vendor.repository.CategoryRefRepository;
import com.ecom.vendor.service.CategoryService;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoryRefRepository categoryRefRepository;

    @Override
  
    public CategoryRef addCategory(CategoryRef categoryRef) {
//        if (categoryRef == null) {
//            throw new IllegalArgumentException("categoryRef cannot be null");
//        }
//
//        Long id = categoryRef.getId();
//        if (id == null) {
//            // new category - ensure we don't persist a client-provided id
//            categoryRef.setId(null);
//            return categoryRefRepository.save(categoryRef);
//        }
//
//        return categoryRefRepository.findById(id).map(existing -> {
//            // update managed entity fields only
//            existing.setName(categoryRef.getName());
//            existing.setSlug(categoryRef.getSlug());
//            existing.setImageUrl(categoryRef.getImageUrl());
//            existing.setCategoryId(categoryRef.getCategoryId());
//            return categoryRefRepository.save(existing);
//        }).orElseGet(() -> {
//            // If the id does not exist in DB, treat as new record (clear id)
//            categoryRef.setId(null);
            return categoryRefRepository.save(categoryRef);
//        });
    }

	@Override
	public List<CategoryRef> getcategory() {
		// TODO Auto-generated method stub
		return categoryRefRepository.findAll();
	}

}
