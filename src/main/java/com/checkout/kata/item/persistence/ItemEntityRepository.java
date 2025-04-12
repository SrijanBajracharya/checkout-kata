package com.checkout.kata.item.persistence;

import com.checkout.kata.item.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEntityRepository extends JpaRepository<ItemEntity, Long> {
}
