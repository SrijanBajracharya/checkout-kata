package com.checkout.kata.item.rest;

import com.checkout.kata.item.domain.dto.request.CreateItemRequest;
import com.checkout.kata.item.domain.dto.response.CreateItemResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemGateway itemGateway;

    @PostMapping
    public ResponseEntity<CreateItemResponse> upsertItem(@RequestBody @Valid CreateItemRequest createItemRequest){
        return ResponseEntity.ok(itemGateway.upsertItem(createItemRequest));
    }

    @GetMapping
    public ResponseEntity<List<String>> getItemsName(){
        return ResponseEntity.ok(itemGateway.getItemNames());
    }
}
