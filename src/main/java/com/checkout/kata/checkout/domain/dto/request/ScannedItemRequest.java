package com.checkout.kata.checkout.domain.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ScannedItemRequest {

    @NotBlank
    private String itemName;

    @NotNull
    private Integer quantity;

}
