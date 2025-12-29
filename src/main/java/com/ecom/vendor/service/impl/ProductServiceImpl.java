package com.ecom.vendor.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.vendor.dto.ItemRequest;
import com.ecom.vendor.dto.ItemResponse;
import com.ecom.vendor.dto.ProductRequest;
import com.ecom.vendor.dto.ProductResponse;
import com.ecom.vendor.dto.VariantDto.VariantDto;
import com.ecom.vendor.entity.CategoryRef;
import com.ecom.vendor.entity.Item;
import com.ecom.vendor.entity.Product;
import com.ecom.vendor.entity.Variant;
import com.ecom.vendor.entity.enums.ProductStatus;
import com.ecom.vendor.repository.CategoryRefRepository;
import com.ecom.vendor.repository.ItemRepository;
import com.ecom.vendor.repository.ProductRepository;
import com.ecom.vendor.service.CloudinaryService;
import com.ecom.vendor.service.ProductService;

import jakarta.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRefRepository categoryRefRepository;

    @Autowired
    private CloudinaryService cloudinaryService;

    @Override
    @Transactional
    public ProductResponse createProduct(ProductRequest req) {
        Product p = new Product();
        p.setName(req.getName());
        p.setBrand(req.getBrand());
        p.setProductDescription(req.getDescription());
        try {
            if (req.getMainImage() != null && !req.getMainImage().isEmpty()) {
                String mainImageUrl = cloudinaryService.uploadImage(req.getMainImage());
                p.setMainImage(mainImageUrl);
            }
            if (req.getAdditionalImages() != null && !req.getAdditionalImages().isEmpty()) {
                List<String> urls = new ArrayList<>();
                for (MultipartFile file : req.getAdditionalImages()) {
                    if (!file.isEmpty()) {
                        String url = cloudinaryService.uploadImage(file);
                        urls.add(url);
                    }
                }
                p.setAdditionalImages(String.join(",", urls));
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload images", e);
        }
        p.setVendorId(req.getVendorId());

        // simple category ref
        CategoryRef cref = categoryRefRepository.findById(req.getCategoryId()).orElseGet(() -> {
            CategoryRef newRef = new CategoryRef();
            newRef.setId(req.getCategoryId());
            newRef.setCategoryId(req.getCategoryId());
            newRef.setName(req.getCategoryName());
            return categoryRefRepository.save(newRef);
        });
        p.setCategoryRef(cref);        // Items
        if (req.getItems() != null) {
            for (ItemRequest ir : req.getItems()) {
                Item item = new Item();
                item.setItemName(ir.getItemName());
                item.setSku(ir.getSku());
                item.setPrice(ir.getPrice());
                item.setMrp(ir.getMrp());
                item.setInventory(ir.getInventory());
                try {
                    if (ir.getItemImage() != null && !ir.getItemImage().isEmpty()) {
                        String itemImageUrl = cloudinaryService.uploadImage(ir.getItemImage());
                        item.setItemImage(itemImageUrl);
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Failed to upload item image", e);
                }
                // variants
                if (ir.getVariants() != null) {
                    ir.getVariants().forEach(v -> {
                        Variant var = new Variant();
                        var.setKeyName(v.getKeyName());
                        var.setValue(v.getValue());
                        item.getVariants().add(var);
                        var.setItem(item);
                    });
                }
                p.addItem(item);
            }
        }

        Product saved = productRepository.save(p);
        return toResponse(saved);
    }

    @Override
    public ProductResponse getProduct(Long id) {
        Product p = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        return toResponse(p);
    }

    @Override
    public List<ProductResponse> listApprovedProducts() {
        List<Product> list = productRepository.findByStatus(ProductStatus.APPROVED);
        List<ProductResponse> out = new ArrayList<>();
        list.forEach(p -> out.add(toResponse(p)));
        return out;
    }

    @Override
    public List<ProductResponse> listPendingProducts() {
        List<Product> list = productRepository.findByStatus(ProductStatus.PENDING);
        List<ProductResponse> out = new ArrayList<>();
        list.forEach(p -> out.add(toResponse(p)));
        return out;
    }

    @Override
    @Transactional
    public ProductResponse approveProduct(Long id) {
        Product p = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        p.setStatus(ProductStatus.APPROVED);
        Product saved = productRepository.save(p);
        return toResponse(saved);
    }

    @Override
    @Transactional
    public ProductResponse rejectProduct(Long id, String remark) {
        Product p = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        p.setStatus(ProductStatus.REJECTED);
        Product saved = productRepository.save(p);
        return toResponse(saved);
    }

    @Override
    public List<ProductResponse> listProductsByVendor(Long vendorId) {
        List<Product> list = productRepository.findByVendorId(vendorId);
        List<ProductResponse> out = new ArrayList<>();
        list.forEach(p -> out.add(toResponse(p)));
        return out;
    }

    private ProductResponse toResponse(Product p) {
        ProductResponse r = new ProductResponse();
        r.setId(p.getId());
        r.setName(p.getName());
        r.setBrand(p.getBrand());
        r.setDescription(p.getProductDescription());
        r.setMainImage(p.getMainImage());
        r.setAdditionalImages(p.getAdditionalImages());
        r.setVendorId(p.getVendorId());
        r.setStatus(p.getStatus().name());
        if (p.getCategoryRef() != null) {
            r.setCategoryId(p.getCategoryRef().getId());
            r.setCategoryName(p.getCategoryRef().getName());
        }
        // items
        List<ItemResponse> items = new ArrayList<>();
        if (p.getItems() != null) {
            for (Item it : p.getItems()) {
                ItemResponse ir = new ItemResponse();
                ir.setId(it.getId());
                ir.setItemName(it.getItemName());
                ir.setSku(it.getSku());
                ir.setPrice(it.getPrice());
                ir.setMrp(it.getMrp());
                ir.setInventory(it.getInventory());
                ir.setItemImage(it.getItemImage());
                ir.setInventoryStatus(it.getInventoryStatus().name());
                // variants
                List<VariantDto> vars = new ArrayList<>();
                if (it.getVariants() != null) {
                    it.getVariants().forEach(v -> {
                        VariantDto vd = new VariantDto();
                        vd.setKeyName(v.getKeyName());
                        vd.setValue(v.getValue());
                        vars.add(vd);
                    });
                    ir.setVariants(vars);
                }
                items.add(ir);
            }
        }
        r.setItems(items);
        return r;
    }

    @Override
    public List<Product> getAllproduct() {
        // TODO Auto-generated method stub
        return productRepository.findAll();
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> list = productRepository.findAll();
        List<ProductResponse> out = new ArrayList<>();
        list.forEach(p -> out.add(toResponse(p)));
        return out;
    }

    @Override
    public List<Product> getapproveproduct() {
        // TODO Auto-generated method stub
        return productRepository.findByStatus(ProductStatus.APPROVED);
    }

    @Override
    public List<Product> getrejectproduct() {
        // TODO Auto-generated method stub
        return productRepository.findByStatus(ProductStatus.REJECTED);
    }

}
