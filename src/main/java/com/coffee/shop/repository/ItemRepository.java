package com.coffee.shop.repository;

import com.coffee.shop.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
