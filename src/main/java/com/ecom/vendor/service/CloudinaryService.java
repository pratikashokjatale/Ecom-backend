package com.ecom.vendor.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.dto.ImageUploadResponse;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryService {

    @Autowired
    private Cloudinary cloudinary;

    public String uploadImage(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("url").toString();
    }

    public String uploadImage(byte[] fileBytes, String fileName) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(fileBytes, ObjectUtils.asMap("public_id", fileName));
        return uploadResult.get("url").toString();
    }

    public ImageUploadResponse uploadImageWithDetails(MultipartFile file) throws IOException {
        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        String imageName = (String) uploadResult.get("original_filename");
        String imageId = (String) uploadResult.get("public_id");
        String url = (String) uploadResult.get("url");
        return new ImageUploadResponse(imageName, imageId, url);
    }
}
