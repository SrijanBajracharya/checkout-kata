package com.checkout.kata.item.rest;

import com.checkout.kata.item.domain.Item;
import com.checkout.kata.item.domain.dto.request.CreateItemRequest;
import com.checkout.kata.item.domain.dto.response.CreateItemResponse;
import com.checkout.kata.item.service.UpsertItemUseCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class ItemGateway {

    private final UpsertItemUseCaseService upsertItemUseCaseService;
    private final ItemDomainMapper itemDomainMapper;

    public CreateItemResponse upsertItem(CreateItemRequest createItemRequest){
        Item item = itemDomainMapper.mapToDomain(createItemRequest);
        Item savedItem = upsertItemUseCaseService.handle(item);
        return itemDomainMapper.mapToResponse(savedItem);
    }
}
