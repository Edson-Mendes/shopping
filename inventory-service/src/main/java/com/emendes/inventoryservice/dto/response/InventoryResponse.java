package com.emendes.inventoryservice.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryResponse {

  private String skuCode;
  private boolean isInStock;

}
