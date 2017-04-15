package com.rx.powerstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.powerstore.entity.Category;

@Service
public interface CategoryService {
	public List<Category> findAll();
	
	public Category findOne(Integer id);
}
