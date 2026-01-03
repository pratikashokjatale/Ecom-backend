package com.ecom.vendor.service;

import java.util.List;

import com.ecom.vendor.entity.CategoryRef;

public interface  CategoryService {

	public CategoryRef addCategory(CategoryRef categoryRef);

	public List<CategoryRef> getcategory();
}
