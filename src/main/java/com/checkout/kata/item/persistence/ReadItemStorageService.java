package com.checkout.kata.item.persistence;

import com.checkout.kata.item.domain.Item;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ReadItemStorageService {

    boolean existsByName(String name);

    Map<String, Item> findByNames(Set<String> names);

    List<String> getAllItemNames();
}
