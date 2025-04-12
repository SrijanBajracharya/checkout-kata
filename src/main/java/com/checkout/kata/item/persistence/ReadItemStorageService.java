package com.checkout.kata.item.persistence;

public interface ReadItemStorageService {

    boolean existsByName(String name);
}
