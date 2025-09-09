package com.afterCheckout.AfterCheckout.repository;

import com.afterCheckout.AfterCheckout.entity.PartnerStore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerStoreRepository extends JpaRepository<PartnerStore, Long> {

    List<PartnerStore> findByCityAndActiveTrue(String city);
}
