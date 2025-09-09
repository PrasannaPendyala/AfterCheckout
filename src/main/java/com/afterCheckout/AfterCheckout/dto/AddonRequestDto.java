package com.afterCheckout.AfterCheckout.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AddonRequestDto {

    @NotNull
    private Long productId;

    @NotNull @Min(1)
    private Integer quantity;

}
