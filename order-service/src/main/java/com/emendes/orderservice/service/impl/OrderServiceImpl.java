package com.emendes.orderservice.service.impl;

import com.emendes.orderservice.dto.request.OrderLineItemsRequest;
import com.emendes.orderservice.dto.request.OrderRequest;
import com.emendes.orderservice.model.Order;
import com.emendes.orderservice.model.OrderLineItems;
import com.emendes.orderservice.repository.OrderRepository;
import com.emendes.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;

  @Override
  public void placeOrder(OrderRequest orderRequest) {
    log.info("ORDER_REQUEST {}", orderRequest);
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());

    List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsList()
        .stream().map(this::mapToModel).toList();

    order.setOrderLineItemsList(orderLineItemsList);
    log.info("ITEM BEFORE ::: {}", order.getOrderLineItemsList().get(0));

    orderRepository.save(order);
    log.info("ITEM AFTER ::: {}", order.getOrderLineItemsList().get(0));
    log.info("order {} placed successfully", order.getOrderNumber());
  }

  private OrderLineItems mapToModel(OrderLineItemsRequest orderLineItemsRequest) {
    OrderLineItems orderLineItems = new OrderLineItems();

    orderLineItems.setSkuCode(orderLineItemsRequest.getSkuCode());
    orderLineItems.setQuantity(orderLineItemsRequest.getQuantity());
    orderLineItems.setPrice(orderLineItemsRequest.getPrice());

    return orderLineItems;
  }

}
