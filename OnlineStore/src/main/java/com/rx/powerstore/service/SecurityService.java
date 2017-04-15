package com.rx.powerstore.service;

public interface SecurityService {
	public String findLoggedInUsername();

	public void autologin(String username, String password);
}
