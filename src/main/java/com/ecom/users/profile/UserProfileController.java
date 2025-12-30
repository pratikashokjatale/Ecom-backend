package com.ecom.users.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.ecom.auth.security.services.UserDetailsImpl;

@RestController
@RequestMapping("/api/user/profile")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping
    public ResponseEntity<Userprofile> createUserProfile(@AuthenticationPrincipal UserDetailsImpl currentUser,
            @RequestParam(value = "userId", required = false) Long userId,
            @RequestBody Userprofile userProfile) {
        // allow two modes:
        // - authenticated: derive userId from JWT principal
        // - unauthenticated: caller must provide userId as request param
        if (currentUser != null) {
            userId = currentUser.getId();
        }
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }
        Userprofile createdProfile = userProfileService.createUserProfile(userId, userProfile);
        return ResponseEntity.ok(createdProfile);
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Userprofile> getUserProfile(@PathVariable Long userId) {
        Userprofile userProfile = userProfileService.getUserProfileByUserId(userId);
        return ResponseEntity.ok(userProfile);
    }
}
