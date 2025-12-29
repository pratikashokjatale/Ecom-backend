package com.ecom.vendor.service;

import java.util.List;

import com.ecom.vendor.dto.ProductRequest;
import com.ecom.vendor.dto.ProductResponse;
import com.ecom.vendor.entity.Product;

public interface ProductService {

    ProductResponse createProduct(ProductRequest req);

    ProductResponse getProduct(Long id);

    List<ProductResponse> listApprovedProducts();

    List<ProductResponse> listPendingProducts();

    ProductResponse approveProduct(Long id);

    ProductResponse rejectProduct(Long id, String remark);

    List<ProductResponse> listProductsByVendor(Long vendorId);

    List<Product> getAllproduct();

    List<Product> getapproveproduct();

    List<Product> getrejectproduct();

    // returns DTOs for all products
    List<ProductResponse> getAllProducts();
}
