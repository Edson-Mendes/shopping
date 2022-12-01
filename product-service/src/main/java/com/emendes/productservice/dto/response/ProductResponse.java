package com.emendes.productservice.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

  private String id;
  private String name;
  private String description;
  private BigDecimal price;

}
