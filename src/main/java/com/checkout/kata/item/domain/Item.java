package com.checkout.kata.item.domain;

import com.checkout.kata.offer.domain.Offer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class Item {

    private Long id;
    private String name;
    private BigDecimal unitPrice;
    private Offer offer;

}
