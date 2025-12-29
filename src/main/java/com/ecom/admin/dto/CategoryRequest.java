package com.ecom.admin.dto;





import java.util.List;

public class CategoryRequest {

    private String name;
    private String imageUrl;
    private List<String> subCategories;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public List<String> getSubCategories() {
		return subCategories;
	}
	public void setSubCategories(List<String> subCategories) {
		this.subCategories = subCategories;
	}

    // Getters & Setters
}
