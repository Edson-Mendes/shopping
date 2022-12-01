package com.emendes.productservice.service.impl;

import com.emendes.productservice.dto.request.ProductRequest;
import com.emendes.productservice.dto.response.ProductResponse;
import com.emendes.productservice.model.Product;
import com.emendes.productservice.repository.ProductRepository;
import com.emendes.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class ProductServiceImpl implements ProductService {

  private final ProductRepository productRepository;

  @Override
  public void createProduct(ProductRequest productRequest) {
    Product product = Product.builder()
        .name(productRequest.getName())
        .description(productRequest.getDescription())
        .price(productRequest.getPrice())
        .build();

    productRepository.save(product);
    log.info("Product {} is saved", product.getId());
  }

  @Override
  public List<ProductResponse> getAllProducts() {
    List<Product> products = productRepository.findAll();
    return products.stream().map(this::mapToProductResponse).toList();
  }

  private ProductResponse mapToProductResponse(Product product) {
    return ProductResponse.builder()
        .id(product.getId())
        .name(product.getName())
        .description(product.getDescription())
        .price(product.getPrice())
        .build();
  }

}
