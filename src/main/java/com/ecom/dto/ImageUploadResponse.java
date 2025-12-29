package com.ecom.dto;

public class ImageUploadResponse {

    private String imageName;
    private String imageId;
    private String url;

    public ImageUploadResponse() {
    }

    public ImageUploadResponse(String imageName, String imageId, String url) {
        this.imageName = imageName;
        this.imageId = imageId;
        this.url = url;
    }

    // Getters and Setters
    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
