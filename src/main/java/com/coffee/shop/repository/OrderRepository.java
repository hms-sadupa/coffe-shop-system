package com.coffee.shop.repository;

import com.coffee.shop.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<CoffeeOrder, Long> {
}
