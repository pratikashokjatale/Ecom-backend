package com.ecom.vendor.entity;



import jakarta.persistence.*;

@Entity
@Table(name = "categories_ref")
public class CategoryRef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // category id from category-service

    private String name;
    private String slug;
    private String imageUrl;
    private Long categoryId;
    

    public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public CategoryRef() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}