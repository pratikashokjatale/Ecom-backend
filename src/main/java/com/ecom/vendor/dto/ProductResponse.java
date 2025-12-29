package com.ecom.vendor.dto;





import java.util.List;

public class ProductResponse {
    private Long id;
    private String name;
    private String brand;
    private String description;
    private String mainImage;
    private String additionalImages;
    private Long vendorId;
    private Long categoryId;
    private String categoryName;
    private String status;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<ItemResponse> getItems() {
		return items;
	}
	public void setItems(List<ItemResponse> items) {
		this.items = items;
	}
	private List<ItemResponse> items;
    // getters/setters
}
