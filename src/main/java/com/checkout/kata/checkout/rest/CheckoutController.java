package com.checkout.kata.checkout.rest;

import com.checkout.kata.checkout.domain.dto.request.CheckoutRequest;
import com.checkout.kata.checkout.domain.dto.response.CheckoutResponse;
import com.checkout.kata.checkout.service.CheckoutUseCaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final CheckoutUseCaseService checkoutUseCaseService;

    @PostMapping
    public ResponseEntity<CheckoutResponse> scan(@RequestBody @Valid CheckoutRequest checkoutRequest){
        return ResponseEntity.ok(checkoutUseCaseService.handle(checkoutRequest));
    }

}
