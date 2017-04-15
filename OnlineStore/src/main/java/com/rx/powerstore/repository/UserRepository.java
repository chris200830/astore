package com.rx.powerstore.repository;

import org.springframework.data.repository.CrudRepository;

import com.rx.powerstore.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	public User findByUsername(String username);
	
	public User findByEmailAddress(String emailAddress);
}