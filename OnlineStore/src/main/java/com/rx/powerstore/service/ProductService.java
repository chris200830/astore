package com.rx.powerstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.powerstore.entity.Product;

@Service
public interface ProductService {
	public Product findOne(Long id);

	public Product findByName(String name);

	public List<Product> findAll();
	
	public List<Product>findByCategoryName(String categoryName);
	
	public List<Product>findByCategoryNameOrderByPriceAsc(String categoryName);
	
	public List<Product>findByCategoryNameOrderByPriceDesc(String categoryName);

	public List<Product>findByCategoryNameAndManufacturer(String categoryName, String manufacturer);
	
	public void save(Product product);

	public void delete(Product product);
	
	public void update(Product product);
}
