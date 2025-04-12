package com.checkout.kata.item.domain.dto.response;

import com.checkout.kata.offer.domain.dto.response.OfferResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CreateItemResponse {

    private Long id;
    private String name;
    private BigDecimal unitPrice;
    private OfferResponse offer;
}
