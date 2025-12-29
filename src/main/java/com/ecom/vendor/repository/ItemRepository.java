package com.ecom.vendor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.vendor.entity.Item;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByProductId(Long productId);
}
