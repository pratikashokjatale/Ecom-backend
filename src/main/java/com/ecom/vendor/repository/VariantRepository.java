package com.ecom.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.vendor.entity.Variant;



public interface VariantRepository extends JpaRepository<Variant, Long> {
}
