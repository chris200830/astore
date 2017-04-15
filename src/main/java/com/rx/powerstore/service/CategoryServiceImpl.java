package com.rx.powerstore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rx.powerstore.entity.Category;
import com.rx.powerstore.repository.CategoryRepository;

@Component
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<Category> findAll() {
		List<Category> categories = new ArrayList<Category>();
		
		for(Category category : categoryRepository.findAll())
			categories.add(category);
		
		return categories;
	}
	
	@Override
	public Category findOne(Integer id) {
		return categoryRepository.findOne(id);
	}
}
