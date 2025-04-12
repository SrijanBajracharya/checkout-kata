package com.checkout.kata.item.domain.dto.request;

import com.checkout.kata.offer.domain.dto.request.OfferRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class CreateItemRequest {

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal unitPrice;

    private OfferRequest offer;

}
