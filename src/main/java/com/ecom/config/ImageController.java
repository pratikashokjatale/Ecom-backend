package com.ecom.config;

import com.ecom.dto.ImageUploadResponse;
import com.ecom.vendor.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<ImageUploadResponse> uploadImage(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @RequestParam(value = "image", required = false) MultipartFile image) {
        MultipartFile actual = (file != null) ? file : image;
        if (actual == null || actual.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        try {
            ImageUploadResponse response = cloudinaryService.uploadImageWithDetails(actual);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
}
