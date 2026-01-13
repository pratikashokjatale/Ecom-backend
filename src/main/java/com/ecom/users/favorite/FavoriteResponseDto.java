package com.ecom.users.favorite;

import java.time.LocalDateTime;

public class FavoriteResponseDto {
    private Long favoriteId;
    private LocalDateTime addedAt;
    private ProductResponseDto product;

    public FavoriteResponseDto() {
    }

    public Long getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Long favoriteId) {
        this.favoriteId = favoriteId;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public ProductResponseDto getProduct() {
        return product;
    }

    public void setProduct(ProductResponseDto product) {
        this.product = product;
    }
}
