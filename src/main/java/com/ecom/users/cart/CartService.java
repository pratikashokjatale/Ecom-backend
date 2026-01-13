package com.ecom.users.cart;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecom.auth.model.User;
import com.ecom.auth.repository.UserRepository;
import com.ecom.vendor.entity.Item;
import com.ecom.vendor.repository.ItemRepository;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public CartItem addToCart(Long userId, Long itemId, Integer qty) {
        Optional<User> u = userRepository.findById(userId);
        if (!u.isPresent()) {
            throw new IllegalArgumentException("Invalid user");
        }
        Optional<Item> it = itemRepository.findById(itemId);
        if (!it.isPresent()) {
            throw new IllegalArgumentException("Invalid item");
        }

        // check if existing cart item for same item
        List<CartItem> existing = cartItemRepository.findByUserId(userId).stream()
                .filter(ci -> ci.getItem().getId().equals(itemId))
                .collect(Collectors.toList());

        if (!existing.isEmpty()) {
            CartItem ci = existing.get(0);
            ci.setQuantity(ci.getQuantity() + (qty == null ? 1 : qty));
            return cartItemRepository.save(ci);
        }

        CartItem cartItem = new CartItem();
        cartItem.setUser(u.get());
        cartItem.setItem(it.get());
        cartItem.setQuantity(qty == null ? 1 : qty);
        return cartItemRepository.save(cartItem);
    }

    public List<CartItem> getCartForUser(Long userId) {
        return cartItemRepository.findByUserId(userId);
    }

    @Transactional
    public void removeFromCart(Long cartItemId, Long userId) {
        Optional<CartItem> ci = cartItemRepository.findById(cartItemId);
        if (ci.isPresent() && ci.get().getUser().getId().equals(userId)) {
            cartItemRepository.deleteById(cartItemId);
        } else {
            throw new IllegalArgumentException("Cart item not found or not owned by user");
        }
    }

    @Transactional
    public void clearCart(Long userId) {
        cartItemRepository.deleteByUserId(userId);
    }
}
