package com.emendes.orderservice.service;

import com.emendes.orderservice.dto.request.OrderRequest;

public interface OrderService {

  void placeOrder(OrderRequest orderRequest);

}
