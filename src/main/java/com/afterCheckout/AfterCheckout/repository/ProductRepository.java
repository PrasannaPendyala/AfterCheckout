package com.afterCheckout.AfterCheckout.repository;

import com.afterCheckout.AfterCheckout.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByPartnerStoreId(Long partnerStoreId);
}
