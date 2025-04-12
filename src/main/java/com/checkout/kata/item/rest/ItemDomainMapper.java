package com.checkout.kata.item.rest;

import com.checkout.kata.item.domain.Item;
import com.checkout.kata.item.domain.dto.request.CreateItemRequest;
import com.checkout.kata.item.domain.dto.response.CreateItemResponse;
import com.checkout.kata.offer.domain.Offer;
import com.checkout.kata.offer.domain.dto.request.OfferRequest;
import com.checkout.kata.offer.domain.dto.response.OfferResponse;
import org.springframework.stereotype.Component;

@Component
public class ItemDomainMapper {

    public Item mapToDomain(CreateItemRequest createItemRequest){
        return Item.builder()
                .name(createItemRequest.getName())
                .unitPrice(createItemRequest.getUnitPrice())
                .offer(mapToOfferDomain(createItemRequest.getOffer()))
                .build();
    }

    public CreateItemResponse mapToResponse(Item item){
        return CreateItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .unitPrice(item.getUnitPrice())
                .offer(mapToOfferResponse(item.getOffer()))
                .build();
    }

    private OfferResponse mapToOfferResponse(Offer offer){
        if(offer == null){
            return null;
        }
        return OfferResponse.builder()
                .id(offer.getId())
                .quantity(offer.getQuantity())
                .discountPrice(offer.getDiscountPrice())
                .build();
    }

    private Offer mapToOfferDomain(OfferRequest offerRequest){
        if(offerRequest == null){
            return null;
        }
        return Offer.builder()
                .quantity(offerRequest.getQuantity())
                .discountPrice(offerRequest.getDiscountPrice())
                .build();
    }

}
