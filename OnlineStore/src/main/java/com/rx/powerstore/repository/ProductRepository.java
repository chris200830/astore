package com.rx.powerstore.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.rx.powerstore.entity.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	public Product findByName(String name);
	
	public List<Product>findByCategoryName(String categoryName);
	public List<Product>findByCategoryNameOrderByPriceAsc(String categoryName);
	public List<Product>findByCategoryNameOrderByPriceDesc(String categoryName);
	public List<Product>findByCategoryNameAndManufacturer(String categoryName, String manufacturer);
}