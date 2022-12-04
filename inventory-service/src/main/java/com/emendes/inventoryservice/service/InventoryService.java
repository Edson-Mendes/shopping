package com.emendes.inventoryservice.service;

import com.emendes.inventoryservice.dto.response.InventoryResponse;

import java.util.List;

public interface InventoryService {

  List<InventoryResponse> isInStock(List<String> skuCodes);

}
