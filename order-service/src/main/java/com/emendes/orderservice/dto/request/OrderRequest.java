package com.emendes.orderservice.dto.request;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

  private List<OrderLineItemsRequest> orderLineItemsList;

}
