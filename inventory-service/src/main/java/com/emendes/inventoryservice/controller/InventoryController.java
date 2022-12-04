package com.emendes.inventoryservice.controller;

import com.emendes.inventoryservice.dto.response.InventoryResponse;
import com.emendes.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

  private final InventoryService inventoryService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<InventoryResponse> isInStock(@RequestParam("sku-code") List<String> skuCodes) {
    return inventoryService.isInStock(skuCodes);
  }

}
