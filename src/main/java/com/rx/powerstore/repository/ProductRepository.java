package com.rx.powerstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.rx.powerstore.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	public Product findByName(String name);
}