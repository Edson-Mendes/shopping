package com.emendes.inventoryservice.service.impl;

import com.emendes.inventoryservice.repository.InventoryRepository;
import com.emendes.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class InventoryServiceImpl implements InventoryService {

  private final InventoryRepository inventoryRepository;

  @Override
  public boolean isInStock(String skuCode) {
    return inventoryRepository.findBySkuCode(skuCode).isPresent();
  }
}
