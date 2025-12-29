package com.ecom.vendor.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProductRequest {

    private String name;
    private String brand;
    private String description;
    private MultipartFile mainImage;
    private List<MultipartFile> additionalImages;
    private Long vendorId;
    private Long categoryId;
    private String categoryName;
    private List<ItemRequest> items;

    // getters/setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getMainImage() {
        return mainImage;
    }

    public void setMainImage(MultipartFile mainImage) {
        this.mainImage = mainImage;
    }

    public List<MultipartFile> getAdditionalImages() {
        return additionalImages;
    }

    public void setAdditionalImages(List<MultipartFile> additionalImages) {
        this.additionalImages = additionalImages;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<ItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemRequest> items) {
        this.items = items;
    }
}
