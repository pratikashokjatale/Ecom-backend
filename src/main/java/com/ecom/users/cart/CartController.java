package com.ecom.users.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.auth.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/api/user/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CartResponseDto> addToCart(@AuthenticationPrincipal UserDetailsImpl currentUser,
            @RequestBody CartRequestDto cartDto) {
        Long userId = cartDto.getUserId();
        Long itemId = cartDto.getItemId();
        Integer qty = cartDto.getQuantity();
        if (currentUser != null) {
            userId = currentUser.getId();
        }
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }
        CartItem ci = cartService.addToCart(userId, itemId, qty);
        return ResponseEntity.ok(mapToDto(ci));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<CartResponseDto>> getCart(@AuthenticationPrincipal UserDetailsImpl currentUser,
            @RequestParam(value = "userId", required = false) Long userId) {
        if (currentUser != null) {
            userId = currentUser.getId();
        }
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }
        List<CartItem> cartItems = cartService.getCartForUser(userId);
        List<CartResponseDto> dtos = cartItems.stream().map(this::mapToDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/remove/{cartItemId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> removeFromCart(@AuthenticationPrincipal UserDetailsImpl currentUser,
            @PathVariable Long cartItemId) {
        Long userId = currentUser.getId();
        cartService.removeFromCart(cartItemId, userId);
        return ResponseEntity.ok().build();
    }

    private CartResponseDto mapToDto(CartItem ci) {
        CartResponseDto dto = new CartResponseDto();
        dto.setCartItemId(ci.getId());
        dto.setQuantity(ci.getQuantity());
        dto.setAddedAt(ci.getAddedAt());

        if (ci.getItem() != null) {
            ItemResponseDto itemDto = new ItemResponseDto();
            itemDto.setItemId(ci.getItem().getId());
            itemDto.setItemName(ci.getItem().getItemName());
            itemDto.setPrice(ci.getItem().getPrice());
            itemDto.setItemImage(ci.getItem().getItemImage());
            itemDto.setInventoryStatus(ci.getItem().getInventoryStatus());
            dto.setItem(itemDto);
        }
        return dto;
    }
}
