package com.afterCheckout.AfterCheckout.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "orderItem")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oderItemId")
    private Long id;

    private String name;

    private Double price;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;
}
