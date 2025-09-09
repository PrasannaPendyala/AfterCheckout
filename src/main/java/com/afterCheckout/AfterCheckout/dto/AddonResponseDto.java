package com.afterCheckout.AfterCheckout.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AddonResponseDto {

    private Long id;

    private Long orderId;

    private Long productId;

    private Integer quantity;

    private Double price;
}
