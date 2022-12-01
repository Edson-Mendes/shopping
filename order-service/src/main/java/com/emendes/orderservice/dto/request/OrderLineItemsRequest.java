package com.emendes.orderservice.dto.request;

import lombok.*;

import java.math.BigDecimal;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsRequest {

  private String skuCode;
  private BigDecimal price;
  private Integer quantity;

}
