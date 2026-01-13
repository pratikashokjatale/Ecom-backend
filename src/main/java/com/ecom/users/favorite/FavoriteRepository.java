package com.ecom.users.favorite;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUserId(Long userId);

    void deleteByUserIdAndProductId(Long userId, Long productId);
}
