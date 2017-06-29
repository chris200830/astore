package com.rx.powerstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rx.powerstore.entity.Product;
import com.rx.powerstore.repository.ProductRepository;

@Component
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product findOne(Long id) {
		return productRepository.findOne(id);
	}

	@Override
	public Product findByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public List<Product> findAll() {
		List<Product> products = new ArrayList<Product>();

		for (Product product : productRepository.findAll())
			products.add(product);

		return products;
	}
	
	@Override
	public List<Product>findByCategoryName(String categoryName) {
		return productRepository.findByCategoryName(categoryName);
	}
	
	@Override
	public List<Product>findByCategoryNameOrderByPriceAsc(String categoryName) {
		return productRepository.findByCategoryNameOrderByPriceAsc(categoryName);
	}
	
	@Override
	public List<Product>findByCategoryNameOrderByPriceDesc(String categoryName) {
		return productRepository.findByCategoryNameOrderByPriceDesc(categoryName);
	}
	
	@Override
	public List<Product>findByCategoryNameAndManufacturer(String categoryName, String manufacturer) {
		return productRepository.findByCategoryNameAndManufacturer(categoryName, manufacturer);
	}
	
	@Override
	public void save(Product product) {
		productRepository.save(product);
	}

	@Override
	public void delete(Product product) {
		productRepository.delete(product);
	}
	
	@Override
	public void update(Product product) {
		findOne(product.getId()).setName(product.getName());
		findOne(product.getId()).setDetails(product.getDetails());
		findOne(product.getId()).setPrice(product.getPrice());
		findOne(product.getId()).setTotalCount(product.getTotalCount());
		findOne(product.getId()).setCategory(product.getCategory());
	}
}
