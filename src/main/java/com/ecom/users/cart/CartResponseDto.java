package com.ecom.users.cart;

import java.time.LocalDateTime;

public class CartResponseDto {
    private Long cartItemId;
    private Integer quantity;
    private LocalDateTime addedAt;
    private ItemResponseDto item;

    public CartResponseDto() {
    }

    public Long getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Long cartItemId) {
        this.cartItemId = cartItemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public ItemResponseDto getItem() {
        return item;
    }

    public void setItem(ItemResponseDto item) {
        this.item = item;
    }
}
