package com.checkout.kata.item.service;

import com.checkout.kata.item.domain.Item;

public interface UpsertItemUseCaseService {

    Item handle(Item item);

}
