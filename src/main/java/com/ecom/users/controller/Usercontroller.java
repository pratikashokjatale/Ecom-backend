package com.ecom.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ecom.vendor.controller.ProductController;
import com.ecom.vendor.dto.ProductResponse;
import com.ecom.vendor.entity.Product;
import com.ecom.vendor.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/user")
@RestController
public class Usercontroller {

    @Autowired
    ProductService productService;

    @GetMapping("/getallproduct")
 @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllproduct());
    }

    @GetMapping("/approved")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ProductResponse>> approved() {
        return ResponseEntity.ok(productService.listApprovedProducts());
    }

}

