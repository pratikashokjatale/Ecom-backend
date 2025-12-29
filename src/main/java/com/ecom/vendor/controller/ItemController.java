package com.ecom.vendor.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecom.vendor.dto.ItemResponse;
import com.ecom.vendor.entity.Item;
import com.ecom.vendor.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/items")
public class ItemController {

  @Autowired
  private ItemRepository itemRepository;

  @GetMapping("/product/{productId}")
  public ResponseEntity<List<ItemResponse>> itemsByProduct(@PathVariable Long productId) {
    List<Item> items = itemRepository.findByProductId(productId);
    List<ItemResponse> list = items.stream().map(it -> {
      ItemResponse ir = new ItemResponse();
      ir.setId(it.getId());
      ir.setItemName(it.getItemName());
      ir.setSku(it.getSku());
      ir.setPrice(it.getPrice());
      ir.setMrp(it.getMrp());
      ir.setInventory(it.getInventory());
      ir.setItemImage(it.getItemImage());
      ir.setInventoryStatus(it.getInventoryStatus().name());
      return ir;
    }).collect(Collectors.toList());
    return ResponseEntity.ok(list);
  }
}
