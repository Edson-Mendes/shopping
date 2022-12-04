package com.emendes.orderservice.service.impl;

import com.emendes.orderservice.dto.request.OrderLineItemsRequest;
import com.emendes.orderservice.dto.request.OrderRequest;
import com.emendes.orderservice.dto.response.InventoryResponse;
import com.emendes.orderservice.model.Order;
import com.emendes.orderservice.model.OrderLineItems;
import com.emendes.orderservice.repository.OrderRepository;
import com.emendes.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class OrderServiceImpl implements OrderService {

  private final OrderRepository orderRepository;
  private final WebClient webClient;

  @Override
  public void placeOrder(OrderRequest orderRequest) {
    Order order = new Order();
    order.setOrderNumber(UUID.randomUUID().toString());

    List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsList()
        .stream().map(this::mapToModel).toList();

    order.setOrderLineItemsList(orderLineItemsList);

    List<String> skuCodes = order.getOrderLineItemsList().stream().map(o -> o.getSkuCode()).toList();
//    Call inventory service, and place order if all products in stock

    InventoryResponse[] inventoryResponseArray = webClient.get().uri("http://localhost:8082/api/inventory",
            uriBuilder ->  uriBuilder.queryParam("sku-code", skuCodes).build())
        .retrieve().bodyToMono(InventoryResponse[].class).block();

    assert inventoryResponseArray != null;
    boolean isInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);

    if (isInStock) {
      orderRepository.save(order);
      log.info("order {} placed successfully", order.getOrderNumber());
    } else {
      throw new IllegalArgumentException("Some product is not in stock, please try again later");
    }

  }

  private OrderLineItems mapToModel(OrderLineItemsRequest orderLineItemsRequest) {
    OrderLineItems orderLineItems = new OrderLineItems();

    orderLineItems.setSkuCode(orderLineItemsRequest.getSkuCode());
    orderLineItems.setQuantity(orderLineItemsRequest.getQuantity());
    orderLineItems.setPrice(orderLineItemsRequest.getPrice());

    return orderLineItems;
  }

}
