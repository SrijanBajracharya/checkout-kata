package com.checkout.kata.checkout.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CheckoutItemResponse {

    private String name;
    private BigDecimal total;
}
