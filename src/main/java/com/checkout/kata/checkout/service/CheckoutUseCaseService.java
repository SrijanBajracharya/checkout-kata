package com.checkout.kata.checkout.service;

import com.checkout.kata.checkout.domain.dto.request.CheckoutRequest;
import com.checkout.kata.checkout.domain.dto.response.CheckoutResponse;

public interface CheckoutUseCaseService {

    CheckoutResponse handle(CheckoutRequest checkoutRequest);
}
