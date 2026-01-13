package com.ecom.users.cart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByUserId(Long userId);

    void deleteByUserId(Long userId);
}
