package com.checkout.kata.item.rest;

import com.checkout.kata.item.domain.dto.request.CreateItemRequest;
import com.checkout.kata.item.domain.dto.response.CreateItemResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/item")
@RequiredArgsConstructor
public class ItemController {

    @PostMapping
    public ResponseEntity<CreateItemResponse> createItem(@RequestBody @Valid CreateItemRequest createItemRequest){
        return null;
    }
}
