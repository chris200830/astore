package com.rx.powerstore.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.rx.powerstore.entity.User;
import com.rx.powerstore.entity.Role;
import com.rx.powerstore.repository.RoleRepository;
import com.rx.powerstore.repository.UserRepository;

@Component
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public List<User> findAll() {
		List<User> users = new ArrayList<User>();
		for (User user : userRepository.findAll())
			users.add(user);

		return users;
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmailAddress(String emailAddress) {
		return userRepository.findByEmailAddress(emailAddress);
	}

	public void save(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findOne((long) 5));
		user.setRoles(roles);

		userRepository.save(user);
	}

	public User findOne(long id) {
		return userRepository.findOne(id);
	}

	public void delete(User user) {
		userRepository.delete(user);
	}
}
