package com.checkout.kata.offer.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OfferRequest {

    private Long quantity;
    private BigDecimal discountPrice;
}
