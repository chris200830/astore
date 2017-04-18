package com.rx.powerstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.powerstore.entity.Product;

@Service
public interface ProductService {
	public Product findOne(Long id);

	public Product findByName(String name);

	public List<Product> findAll();

	public void save(Product product);

	public void delete(Product product);
}
