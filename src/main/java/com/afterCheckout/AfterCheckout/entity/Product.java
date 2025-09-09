package com.afterCheckout.AfterCheckout.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    public Long id;

    private String name;

    private Double price;

    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "partnerStoreId")
    private PartnerStore partnerStore;

}
