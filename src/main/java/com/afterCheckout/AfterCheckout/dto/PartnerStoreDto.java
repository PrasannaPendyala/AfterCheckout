package com.afterCheckout.AfterCheckout.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class PartnerStoreDto {

    private Long id;

    private String storeName;

    private String storeType;

    private String city;
}
