package com.ecom.vendor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.ecom.vendor.dto.ProductResponse;
import com.ecom.vendor.entity.CategoryRef;
import com.ecom.vendor.entity.Product;
import com.ecom.vendor.service.CategoryService;
import com.ecom.vendor.service.ProductService;


/*
  This controller is intended to be called by Admin-Service (internal)
  Example:
    PUT /internal/products/{id}/approve
    PUT /internal/products/{id}/reject
 */
@RestController
@RequestMapping("/internal/products")
public class InternalAdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> approve(@PathVariable Long id) {
        return ResponseEntity.ok(productService.approveProduct(id));
    }

    @GetMapping("/getallproduct")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Product> getMethodName() {
        return productService.getAllproduct();
    }

    @GetMapping("/getallproduct/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Product> getaproved() {
        return productService.getapproveproduct();
    }

    @GetMapping("/getallproduct/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Product> getreject() {
        return productService.getrejectproduct();
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductResponse> reject(@PathVariable Long id, @RequestParam(required = false) String remark) {
        return ResponseEntity.ok(productService.rejectProduct(id, remark));
    }

    @PostMapping("/addcategory")
    public ResponseEntity<CategoryRef> addCategory(@RequestBody CategoryRef categoryRef) {
        CategoryRef saved = categoryService.addCategory(categoryRef);
        return ResponseEntity.ok(saved);
    }
}
