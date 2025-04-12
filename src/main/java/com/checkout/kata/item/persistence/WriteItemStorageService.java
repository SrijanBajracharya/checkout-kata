package com.checkout.kata.item.persistence;

import com.checkout.kata.item.domain.Item;

public interface WriteItemStorageService {

    Item create(Item item);

    Item update(Item item);
}
