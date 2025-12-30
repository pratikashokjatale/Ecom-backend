package com.ecom.vendor.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.vendor.entity.CategoryRef;
import com.ecom.vendor.repository.CategoryRefRepository;
import com.ecom.vendor.service.CategoryService;

@Service
public class CategoryServiceImp  implements CategoryService{
	
	@Autowired 
	private CategoryRefRepository categoryRefRepository;
	

	@Override

	public CategoryRef addCategory( CategoryRef categoryRef) {
		return categoryRefRepository.save(categoryRef) ;
	}

}
