package com.ecom.vendor.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ecom.vendor.entity.enums.ProductStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;

    @Column(length = 5000)
    private String productDescription;

    private String mainImage;
    @Column(length = 2000)
    private String additionalImages; // JSON or CSV of urls

    private Long vendorId;

    @Enumerated(EnumType.STRING)
    private ProductStatus status = ProductStatus.PENDING;

    @ManyToOne
    @JoinColumn(name = "category_ref_id")
    private CategoryRef categoryRef;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product() {
    }

    // getters & setters
    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Add helper to add item
    public void addItem(Item item) {
        item.setProduct(this);
        items.add(item);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getAdditionalImages() {
        return additionalImages;
    }

    public void setAdditionalImages(String additionalImages) {
        this.additionalImages = additionalImages;
    }

    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public CategoryRef getCategoryRef() {
        return categoryRef;
    }

    public void setCategoryRef(CategoryRef categoryRef) {
        this.categoryRef = categoryRef;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // getters and setters omitted for brevity — include all in file
    // ...
}
