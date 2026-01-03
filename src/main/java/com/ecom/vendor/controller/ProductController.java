package com.ecom.vendor.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.vendor.dto.ProductRequest;
import com.ecom.vendor.dto.ProductResponse;
import com.ecom.vendor.service.ProductService;

@RestController
@RequestMapping("/products")

public class ProductController {

    @Autowired
    private ProductService productService;

    // Public: list approved products
    @GetMapping("/approved")
    public ResponseEntity<List<ProductResponse>> approved() {
        return ResponseEntity.ok(productService.listApprovedProducts());
    }

    // Vendor: create product (goes to PENDING)
    @PostMapping(value = "/vendor")
    public ResponseEntity<ProductResponse> create(
            @RequestBody ProductRequest req)
     {

       

        return ResponseEntity.ok(productService.createProduct(req));
    }

    // Vendor: create product via JSON payload (image URLs supported)
//    @PostMapping(value = "/vendor")
//    public ResponseEntity<ProductResponse> createJson(@RequestBody ProductRequest req) {
//        return ResponseEntity.ok(productService.createProduct(req));
//    }

    // Vendor: list own products
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<List<ProductResponse>> vendorProducts(@PathVariable Long vendorId) {
        return ResponseEntity.ok(productService.listProductsByVendor(vendorId));
    }

    // Admin: list pending products
    @GetMapping("/admin/pending")
    public ResponseEntity<List<ProductResponse>> pending() {
        return ResponseEntity.ok(productService.listPendingProducts());
    }

    // Public: list all products (DTOs)
    @GetMapping("/all")
    public ResponseEntity<List<ProductResponse>> all() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Admin: approve product
    @PutMapping("/admin/approve/{id}")
    public ResponseEntity<ProductResponse> approve(@PathVariable Long id) {
        return ResponseEntity.ok(productService.approveProduct(id));
    }

    // Admin: reject product
    @PutMapping("/admin/reject/{id}")
    public ResponseEntity<ProductResponse> reject(@PathVariable Long id, @RequestParam String remark) {
        return ResponseEntity.ok(productService.rejectProduct(id, remark));
    }
}
