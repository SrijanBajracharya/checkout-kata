package com.checkout.kata.checkout.utils;

import com.checkout.kata.checkout.domain.dto.request.ScannedItemRequest;
import com.checkout.kata.checkout.domain.dto.response.CheckoutResponse;
import com.checkout.kata.item.domain.Item;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CheckoutCalculationUtil {

    public static CheckoutResponse handleCheckout(List<ScannedItemRequest> scannedItems, Map<String, Item> itemsMap) {
        return null;
    }

}
