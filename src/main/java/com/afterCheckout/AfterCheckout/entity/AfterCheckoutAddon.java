package com.afterCheckout.AfterCheckout.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class AfterCheckoutAddon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "OrderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private Integer quantity;

    private Double price;
}
