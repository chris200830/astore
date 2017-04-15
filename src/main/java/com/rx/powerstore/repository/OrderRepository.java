package com.rx.powerstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.rx.powerstore.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
