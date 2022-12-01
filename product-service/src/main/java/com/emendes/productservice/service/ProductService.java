package com.emendes.productservice.service;

import com.emendes.productservice.dto.request.ProductRequest;
import com.emendes.productservice.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

  void createProduct(ProductRequest productRequest);

  List<ProductResponse> getAllProducts();
}
