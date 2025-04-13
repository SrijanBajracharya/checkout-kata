package com.checkout.kata.item.service;

import com.checkout.kata.item.domain.Item;
import com.checkout.kata.item.persistence.ReadItemStorageService;
import com.checkout.kata.item.persistence.WriteItemStorageService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultUpsertItemUseCaseService implements UpsertItemUseCaseService {

    private final WriteItemStorageService writeItemStorageService;
    private final ReadItemStorageService readItemStorageService;

    @Transactional
    @Override
    public Item handle(Item item) {
        if(readItemStorageService.existsByName(item.getName())){
            return writeItemStorageService.update(item);
        }
        return writeItemStorageService.create(item);
    }
}
