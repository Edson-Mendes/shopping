package com.emendes.inventoryservice.service.impl;

import com.emendes.inventoryservice.dto.response.InventoryResponse;
import com.emendes.inventoryservice.repository.InventoryRepository;
import com.emendes.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class InventoryServiceImpl implements InventoryService {

  private final InventoryRepository inventoryRepository;

  @Override
  public List<InventoryResponse> isInStock(List<String> skuCodes) {
    return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
        .map(i -> InventoryResponse.builder()
            .skuCode(i.getSkuCode())
            .isInStock(i.getQuantity() > 0)
            .build()
        ).toList();
  }

}
