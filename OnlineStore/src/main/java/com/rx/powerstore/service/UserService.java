package com.rx.powerstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rx.powerstore.entity.User;

@Service
public interface UserService {
	public List<User> findAll();

	public User findByUsername(String username);
	
	public void save(User user);
}
