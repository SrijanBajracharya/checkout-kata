package com.checkout.kata.checkout.service;

import com.checkout.kata.checkout.domain.dto.request.CheckoutRequest;
import com.checkout.kata.checkout.domain.dto.request.ScannedItemRequest;
import com.checkout.kata.checkout.domain.dto.response.CheckoutResponse;
import com.checkout.kata.checkout.utils.CheckoutCalculationUtil;
import com.checkout.kata.item.domain.Item;
import com.checkout.kata.item.persistence.ReadItemStorageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultCheckoutUseCaseService implements CheckoutUseCaseService {

    private final ReadItemStorageService readItemStorageService;

    @Transactional
    @Override
    public CheckoutResponse handle(CheckoutRequest checkoutRequest){
        Set<String> itemsNames = checkoutRequest.getItems().stream()
                .map(ScannedItemRequest::getItemName)
                .collect(Collectors.toSet());
        Map<String, Item> itemsMap = readItemStorageService.findByNames(itemsNames);


        return CheckoutCalculationUtil.handleCheckout(checkoutRequest.getItems(), itemsMap);
    }

}
