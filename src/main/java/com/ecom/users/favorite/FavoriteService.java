package com.ecom.users.favorite;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.auth.model.User;
import com.ecom.auth.repository.UserRepository;
import com.ecom.vendor.entity.Product;
import com.ecom.vendor.repository.ProductRepository;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Favorite addFavorite(Long userId, Long productId) {
        Optional<User> u = userRepository.findById(userId);
        Optional<Product> p = productRepository.findById(productId);
        if (!u.isPresent() || !p.isPresent()) {
            throw new IllegalArgumentException("Invalid user or product");
        }
        Favorite f = new Favorite();
        f.setUser(u.get());
        f.setProduct(p.get());
        return favoriteRepository.save(f);
    }

    public List<Favorite> listFavorites(Long userId) {
        return favoriteRepository.findByUserId(userId);
    }

    @Transactional
    public void removeFavorite(Long userId, Long productId) {
        favoriteRepository.deleteByUserIdAndProductId(userId, productId);
    }
}
