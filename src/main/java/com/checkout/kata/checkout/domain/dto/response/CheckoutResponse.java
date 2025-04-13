package com.checkout.kata.checkout.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class CheckoutResponse {

    private List<CheckoutItemResponse> items;
    private BigDecimal total;

}
