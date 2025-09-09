package com.afterCheckout.AfterCheckout.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "partnerStore")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PartnerStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partnerStoreId")
    private Long id;

    private String storeName;

    private String storeType;

    private String city;

    private Boolean active;

    @OneToMany(mappedBy = "partnerStore", cascade = CascadeType.ALL)
    private List<Product> products;
}
