package com.checkout.kata.offer.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class OfferResponse {

    private Long id;
    private Long quantity;
    private BigDecimal discountPrice;
}
