package com.checkout.kata.item.persistence;

import com.checkout.kata.exception.UseCaseException;
import com.checkout.kata.item.domain.Item;
import com.checkout.kata.item.entity.ItemEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class DefaultWriteItemStorageService implements WriteItemStorageService {

    private final ItemEntityRepository itemEntityRepository;
    private final ItemEntityMapper itemEntityMapper;

    @Override
    public Item create(Item item) {
        ItemEntity entityToSave = itemEntityMapper.mapToEntity(item);
        return itemEntityMapper.mapToDomain(itemEntityRepository.save(entityToSave));
    }

    @Override
    public Item update(Item item) {
        ItemEntity existingEntity = itemEntityRepository.findByName(item.getName())
                .orElseThrow(() -> new UseCaseException("Cannot find item with name: " + item.getName()));

        itemEntityMapper.mapToEntity(existingEntity, item);
        return itemEntityMapper.mapToDomain(itemEntityRepository.save(existingEntity));
    }
}
