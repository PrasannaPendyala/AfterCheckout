package com.afterCheckout.AfterCheckout.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductDto {

    private Long id;

    private String name;

    private Double price;

    private Integer stock;

    private Long partnerStoreId;
}
