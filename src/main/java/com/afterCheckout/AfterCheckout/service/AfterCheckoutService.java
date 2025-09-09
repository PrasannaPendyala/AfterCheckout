package com.afterCheckout.AfterCheckout.service;

import com.afterCheckout.AfterCheckout.dto.AddonRequestDto;
import com.afterCheckout.AfterCheckout.dto.AddonResponseDto;
import com.afterCheckout.AfterCheckout.dto.PartnerStoreDto;

import java.util.List;

public interface AfterCheckoutService {

    List<PartnerStoreDto> getEligibleStores(Long orderId, String city);

    AddonResponseDto addAddon(Long orderId, AddonRequestDto request);

    List<AddonResponseDto>  getAddonsForOrder(Long orderId);
}
