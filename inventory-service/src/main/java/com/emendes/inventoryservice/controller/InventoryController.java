package com.emendes.inventoryservice.controller;

import com.emendes.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

  private final InventoryService inventoryService;

  @GetMapping("/{sku-code}")
  @ResponseStatus(HttpStatus.OK)
  public boolean isInStock(@PathVariable(name = "sku-code") String skuCode) {
    return inventoryService.isInStock(skuCode);
  }

}
