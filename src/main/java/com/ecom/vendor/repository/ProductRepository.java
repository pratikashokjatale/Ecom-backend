package com.ecom.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.vendor.entity.Product;
import com.ecom.vendor.entity.enums.ProductStatus;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStatus(ProductStatus status);
    List<Product> findByVendorId(Long vendorId);
}
