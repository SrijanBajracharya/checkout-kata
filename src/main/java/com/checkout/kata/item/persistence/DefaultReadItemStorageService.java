package com.checkout.kata.item.persistence;

import com.checkout.kata.item.domain.Item;
import com.checkout.kata.item.entity.ItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultReadItemStorageService implements ReadItemStorageService {

    private final ItemEntityRepository itemEntityRepository;
    private final ItemEntityMapper itemEntityMapper;

    @Override
    public boolean existsByName(String name) {
        return itemEntityRepository.existsByName(name);
    }

    @Override
    public Map<String, Item> findByNames(Set<String> names) {
        List<ItemEntity> savedItems = itemEntityRepository.findByNameIn(names);
        List<Item> items = savedItems.stream().map(itemEntityMapper::mapToDomain).toList();
        return items.stream().collect(Collectors.toMap(Item::getName, Function.identity()));
    }

    @Override
    public List<String> getAllItemNames() {
        return itemEntityRepository.findAllNames();
    }

}
