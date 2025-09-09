package com.afterCheckout.AfterCheckout.controller;

import com.afterCheckout.AfterCheckout.dto.AddonRequestDto;
import com.afterCheckout.AfterCheckout.dto.AddonResponseDto;
import com.afterCheckout.AfterCheckout.dto.PartnerStoreDto;
import com.afterCheckout.AfterCheckout.service.AfterCheckoutService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/afterCheckout")
public class AfterCheckoutController {

    private final AfterCheckoutService afterCheckoutService;


    public AfterCheckoutController(AfterCheckoutService afterCheckoutService) {
        this.afterCheckoutService = afterCheckoutService;
    }

    @GetMapping("/eligibleStore/{orderId}")
    public ResponseEntity<List<PartnerStoreDto>> eligibleStores(@PathVariable Long orderId,
                                                                @RequestParam String city) {
        return ResponseEntity.ok(afterCheckoutService.getEligibleStores(orderId, city));
    }

    @PostMapping("/{orderId}/addons")
    public ResponseEntity<AddonResponseDto> addAddon(@PathVariable Long orderId,
                                                     @RequestBody @Valid AddonRequestDto request) {
        return ResponseEntity.ok(afterCheckoutService.addAddon(orderId, request));
    }

    @GetMapping("/{orderId}/addons")
    public ResponseEntity<List<AddonResponseDto>> getAddons(@PathVariable Long orderId) {
        return ResponseEntity.ok(afterCheckoutService.getAddonsForOrder(orderId));
    }
}
