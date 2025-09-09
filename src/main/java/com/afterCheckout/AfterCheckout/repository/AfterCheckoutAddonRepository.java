package com.afterCheckout.AfterCheckout.repository;

import com.afterCheckout.AfterCheckout.entity.AfterCheckoutAddon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AfterCheckoutAddonRepository extends JpaRepository<AfterCheckoutAddon, Long> {

    List<AfterCheckoutAddon> findByOrderId(Long orderId);
}
