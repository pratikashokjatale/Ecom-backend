package com.ecom.vendor.entity;





import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.ecom.vendor.entity.enums.InventoryStatus;


@Entity
@Table(name = "items")
public class Item {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String itemName;
  private String sku;
  private Double price;
  private Double mrp;
  private Integer inventory; // total stock
  private String itemImage;

  @Enumerated(EnumType.STRING)
  private InventoryStatus inventoryStatus = InventoryStatus.IN_STOCK;

  @ManyToOne
  @JoinColumn(name = "product_id")
  private Product product;

  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Variant> variants = new ArrayList<>();

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public Item() {}

  @PrePersist
  public void prePersist() {
    createdAt = LocalDateTime.now();
    updatedAt = createdAt;
    updateInventoryStatus();
  }

  @PreUpdate
  public void preUpdate() {
    updatedAt = LocalDateTime.now();
    updateInventoryStatus();
  }

  public void updateInventoryStatus() {
    if (inventory == null || inventory <= 0) {
      inventoryStatus = InventoryStatus.OUT_OF_STOCK;
    } else if (inventory <= 5) {
      inventoryStatus = InventoryStatus.LOW_STOCK;
    } else {
      inventoryStatus = InventoryStatus.IN_STOCK;
    }
  }

  public Long getId() {
	return id;
  }

  public void setId(Long id) {
	this.id = id;
  }

  public String getItemName() {
	return itemName;
  }

  public void setItemName(String itemName) {
	this.itemName = itemName;
  }

  public String getSku() {
	return sku;
  }

  public void setSku(String sku) {
	this.sku = sku;
  }

  public Double getPrice() {
	return price;
  }

  public void setPrice(Double price) {
	this.price = price;
  }

  public Double getMrp() {
	return mrp;
  }

  public void setMrp(Double mrp) {
	this.mrp = mrp;
  }

  public Integer getInventory() {
	return inventory;
  }

  public void setInventory(Integer inventory) {
	this.inventory = inventory;
  }

  public String getItemImage() {
	return itemImage;
  }

  public void setItemImage(String itemImage) {
	this.itemImage = itemImage;
  }

  public InventoryStatus getInventoryStatus() {
	return inventoryStatus;
  }

  public void setInventoryStatus(InventoryStatus inventoryStatus) {
	this.inventoryStatus = inventoryStatus;
  }

  public Product getProduct() {
	return product;
  }

  public void setProduct(Product product) {
	this.product = product;
  }

  public List<Variant> getVariants() {
	return variants;
  }

  public void setVariants(List<Variant> variants) {
	this.variants = variants;
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

  // getters & setters (include all)
  // ...
}
