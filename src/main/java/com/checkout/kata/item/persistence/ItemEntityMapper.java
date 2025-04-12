package com.checkout.kata.item.persistence;

import com.checkout.kata.item.domain.Item;
import com.checkout.kata.item.entity.ItemEntity;
import com.checkout.kata.offer.domain.Offer;
import com.checkout.kata.offer.entity.OfferEntity;
import org.springframework.stereotype.Component;

@Component
class ItemEntityMapper {

    public ItemEntity mapToEntity(Item item){
        ItemEntity entity = new ItemEntity();
        entity.setId(item.getId());
        entity.setName(item.getName());
        entity.setUnitPrice(item.getUnitPrice());
        entity.setOffer(mapToOfferEntity(item.getOffer(), entity));
        return entity;
    }

    public void mapToEntity(ItemEntity savedEntity, Item item){
        savedEntity.setUnitPrice(item.getUnitPrice());
        savedEntity.setOffer(mapToOfferEntity(item.getOffer(), savedEntity));
    }

    private OfferEntity mapToOfferEntity(Offer offer, ItemEntity itemEntity){
        if(offer == null){
            return null;
        }
        OfferEntity offerEntity = new OfferEntity();
        offerEntity.setId(offer.getId());
        offerEntity.setQuantity(offer.getQuantity());
        offerEntity.setDiscountPrice(offer.getDiscountPrice());
        offerEntity.setItem(itemEntity);
        return offerEntity;
    }

    public Item mapToDomain(ItemEntity entity){
        return Item.builder()
                .id(entity.getId())
                .name(entity.getName())
                .unitPrice(entity.getUnitPrice())
                .offer(mapToOfferDomain(entity.getOffer()))
                .build();
    }

    private Offer mapToOfferDomain(OfferEntity offerEntity){
        if(offerEntity == null){
            return null;
        }
        return Offer.builder()
                .id(offerEntity.getId())
                .quantity(offerEntity.getQuantity())
                .discountPrice(offerEntity.getDiscountPrice())
                .build();
    }
}
