package com.ecom.users.favorite;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.auth.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/api/user/favorites")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<FavoriteResponseDto> addFavorite(@AuthenticationPrincipal UserDetailsImpl currentUser,
            @RequestBody FavoriteRequestDto favoriteDto) {
        Long userId = favoriteDto.getUserId();
        Long productId = favoriteDto.getProductId();
        if (currentUser != null) {
            userId = currentUser.getId();
        }
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }
        Favorite f = favoriteService.addFavorite(userId, productId);
        return ResponseEntity.ok(mapToDto(f));
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<FavoriteResponseDto>> listFavorites(@AuthenticationPrincipal UserDetailsImpl currentUser,
            @RequestParam(value = "userId", required = false) Long userId) {
        if (currentUser != null) {
            userId = currentUser.getId();
        }
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }
        List<Favorite> favorites = favoriteService.listFavorites(userId);
        List<FavoriteResponseDto> dtos = favorites.stream().map(this::mapToDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @DeleteMapping("/remove")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> removeFavorite(@AuthenticationPrincipal UserDetailsImpl currentUser,
            @RequestParam Long productId) {
        Long userId = currentUser.getId();
        favoriteService.removeFavorite(userId, productId);
        return ResponseEntity.ok().build();
    }

    private FavoriteResponseDto mapToDto(Favorite f) {
        FavoriteResponseDto dto = new FavoriteResponseDto();
        dto.setFavoriteId(f.getId());
        dto.setAddedAt(f.getAddedAt());

        if (f.getProduct() != null) {
            ProductResponseDto pDto = new ProductResponseDto();
            pDto.setProductId(f.getProduct().getId());
            pDto.setName(f.getProduct().getName());
            pDto.setBrand(f.getProduct().getBrand());
            pDto.setMainImage(f.getProduct().getMainImage());
            dto.setProduct(pDto);
        }
        return dto;
    }
}
