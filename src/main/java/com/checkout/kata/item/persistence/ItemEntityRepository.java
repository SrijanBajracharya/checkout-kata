package com.checkout.kata.item.persistence;

import com.checkout.kata.item.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, Long> {

    boolean existsByName(String name);

    Optional<ItemEntity> findByName(String name);
}
