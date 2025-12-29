package com.ecom.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.vendor.entity.CategoryRef;

@Repository
public interface CategoryRefRepository extends JpaRepository<CategoryRef, Long> {
}