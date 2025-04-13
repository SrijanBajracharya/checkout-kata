package com.checkout.kata.checkout.utils;

import com.checkout.kata.checkout.domain.dto.request.ScannedItemRequest;
import com.checkout.kata.checkout.domain.dto.response.CheckoutItemResponse;
import com.checkout.kata.checkout.domain.dto.response.CheckoutResponse;
import com.checkout.kata.exception.UseCaseException;
import com.checkout.kata.item.domain.Item;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CheckoutCalculationUtil {

    /**
     * Groups items by name, applies offer and calculates each item total and the grand total
     * @param scannedItems
     * The items scanned for purchase
     * @param itemsMap
     * The Map of itemName and the item with offer
     * @return CheckoutResponse
     * grand total and list of checkout items with each item total
     */
    public static CheckoutResponse handleCheckout(List<ScannedItemRequest> scannedItems, Map<String, Item> itemsMap) {

        Map<String, Integer> scannedItemMap = scannedItems.stream()
                .collect(Collectors.groupingBy(
                        ScannedItemRequest::getItemName,
                        Collectors.summingInt(ScannedItemRequest::getQuantity)
                ));

        BigDecimal total = BigDecimal.ZERO;
        List<CheckoutItemResponse> items = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : scannedItemMap.entrySet()) {
            String itemName = entry.getKey();
            Item item = itemsMap.get(itemName);
            if (item == null) {
                throw new UseCaseException("Item not found: " + itemName);
            }
            BigDecimal itemTotal = calculateItemTotal(item, entry.getValue());
            items.add(CheckoutItemResponse.builder()
                    .name(item.getName())
                    .total(itemTotal)
                    .build());

            total = total.add(itemTotal);
        }

        return CheckoutResponse.builder()
                .items(items)
                .total(total)
                .build();
    }

    private static BigDecimal calculateItemTotal(Item item, Integer quantity) {
        if (item.getOffer() != null && quantity >= item.getOffer().getQuantity()) {
            int offerQty = item.getOffer().getQuantity();
            int offerApplications = quantity / offerQty;
            int remaining = quantity % offerQty;
            return item.getOffer().getDiscountPrice().multiply(BigDecimal.valueOf(offerApplications))
                    .add(item.getUnitPrice().multiply(BigDecimal.valueOf(remaining)));
        }
        return item.getUnitPrice().multiply(BigDecimal.valueOf(quantity));
    }
}
