package com.emendes.inventoryservice.service.impl;

import com.emendes.inventoryservice.dto.response.InventoryResponse;
import com.emendes.inventoryservice.repository.InventoryRepository;
import com.emendes.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class InventoryServiceImpl implements InventoryService {

  private final InventoryRepository inventoryRepository;

  @Override
  public List<InventoryResponse> isInStock(List<String> skuCodes) {
//    log.info("Wait started");
//    try {
//      Thread.sleep(10000);
//    } catch (Exception ex) {
//      log.error("Algo deu errado dentro do método isInStock");
//    }
//    log.info("Wait ended");
    return inventoryRepository.findBySkuCodeIn(skuCodes).stream()
        .map(i -> InventoryResponse.builder()
            .skuCode(i.getSkuCode())
            .isInStock(i.getQuantity() > 0)
            .build()
        ).toList();
  }

}
