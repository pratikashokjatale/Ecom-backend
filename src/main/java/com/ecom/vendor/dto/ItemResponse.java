package com.ecom.vendor.dto;





import java.util.List;

import com.ecom.vendor.dto.VariantDto.VariantDto;



public class ItemResponse {
    private Long id;
    private String itemName;
    private String sku;
    private Double price;
    private Double mrp;
    private Integer inventory;
    private String inventoryStatus;
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
	public String getInventoryStatus() {
		return inventoryStatus;
	}
	public void setInventoryStatus(String inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}
	public String getItemImage() {
		return itemImage;
	}
	public void setItemImage(String itemImage) {
		this.itemImage = itemImage;
	}
	public List<VariantDto> getVariants() {
		return variants;
	}
	public void setVariants(List<VariantDto> variants) {
		this.variants = variants;
	}
	private String itemImage;
    private List<VariantDto> variants;
    // getters/setters
}
