package com.checkout.kata.item.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DefaultReadItemStorageService implements ReadItemStorageService {

    private final ItemEntityRepository itemEntityRepository;

    @Override
    public boolean existsByName(String name) {
        return itemEntityRepository.existsByName(name);
    }
}
