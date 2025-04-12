package com.checkout.kata.offer.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Offer {

    private Long id;
    private Long quantity;
    private BigDecimal discountPrice;

}
