package com.ecom.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.admin.model.Category;



public interface CategoryRepository extends JpaRepository<Category, Long> {
}