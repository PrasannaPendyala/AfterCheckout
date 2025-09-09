package com.afterCheckout.AfterCheckout.service.Impl;

import com.afterCheckout.AfterCheckout.dto.AddonRequestDto;
import com.afterCheckout.AfterCheckout.dto.AddonResponseDto;
import com.afterCheckout.AfterCheckout.dto.PartnerStoreDto;
import com.afterCheckout.AfterCheckout.entity.AfterCheckoutAddon;
import com.afterCheckout.AfterCheckout.entity.Order;
import com.afterCheckout.AfterCheckout.entity.Product;
import com.afterCheckout.AfterCheckout.exception.ResourceNotFoundException;
import com.afterCheckout.AfterCheckout.repository.AfterCheckoutAddonRepository;
import com.afterCheckout.AfterCheckout.repository.OrderRepository;
import com.afterCheckout.AfterCheckout.repository.PartnerStoreRepository;
import com.afterCheckout.AfterCheckout.repository.ProductRepository;
import com.afterCheckout.AfterCheckout.service.AfterCheckoutService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AfterCheckoutServiceImpl implements AfterCheckoutService {

    private final OrderRepository orderRepository;
    private final PartnerStoreRepository partnerStoreRepository;
    private final ProductRepository productRepository;
    private final AfterCheckoutAddonRepository afterCheckoutAddonRepository;

    public AfterCheckoutServiceImpl(OrderRepository orderRepository,
                                    PartnerStoreRepository partnerStoreRepository,
                                    ProductRepository productRepository,
                                    AfterCheckoutAddonRepository afterCheckoutAddonRepository) {
        this.orderRepository = orderRepository;
        this.partnerStoreRepository = partnerStoreRepository;
        this.productRepository = productRepository;
        this.afterCheckoutAddonRepository = afterCheckoutAddonRepository;

    }


    @Override
    public List<PartnerStoreDto> getEligibleStores(Long orderId, String city) {
        orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not Found"));
        return partnerStoreRepository.findByCityAndActiveTrue(city)
                .stream()
                .map(s -> PartnerStoreDto.builder()
                        .id(s.getId())
                        .storeName(s.getStoreName())
                        .storeType(s.getStoreType())
                        .city(s.getCity())
                        .build())
                .collect(Collectors.toList());

    }

    @Override
    public AddonResponseDto addAddon(Long orderId, AddonRequestDto request) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        if(product.getStock() < request.getQuantity()) {
            throw new IllegalArgumentException("Not enough stock");
        }

        double price = product.getPrice() * request.getQuantity();

        AfterCheckoutAddon addon = AfterCheckoutAddon.builder()
                .order(order)
                .product(product)
                .quantity(request.getQuantity())
                .price(price)
                .build();

        addon = afterCheckoutAddonRepository.save(addon);

        // reduce stock
        product.setStock(product.getStock() - request.getQuantity());
        productRepository.save(product);

        // update order total
        if (order.getTotalAmount() == null) order.setTotalAmount(0.0);
        order.setTotalAmount(order.getTotalAmount() + price);
        if (order.getCreatedAt() == null) order.setCreatedAt(LocalDateTime.now());
        orderRepository.save(order);

        return AddonResponseDto.builder()
                .id(addon.getId())
                .orderId(order.getId())
                .productId(product.getId())
                .quantity(addon.getQuantity())
                .price(addon.getPrice())
                .build();

    }

    @Override
    public List<AddonResponseDto> getAddonsForOrder(Long orderId) {
        orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not Found"));
        return afterCheckoutAddonRepository.findByOrderId(orderId).stream().map(a ->
                AddonResponseDto.builder()
                        .id(a.getId())
                        .orderId(a.getOrder().getId())
                        .productId(a.getProduct().getId())
                        .quantity(a.getQuantity())
                        .price(a.getPrice())
                        .build())
                .collect(Collectors.toList());

    }
}
