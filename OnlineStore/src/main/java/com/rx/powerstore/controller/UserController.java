package com.rx.powerstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rx.powerstore.entity.User;
import com.rx.powerstore.service.SecurityService;
import com.rx.powerstore.service.UserService;
import com.rx.powerstore.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private SecurityService securityService;
	@Autowired
	private UserValidator userValidator;

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		model.addAttribute("userForm", new User());

		return "registration";
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registration(@Valid @ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors())
			return "registration";

		userService.save(userForm);
		securityService.autologin(userForm.getUsername(), userForm.getPassword());

		return "/";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {

		if (error != null)
			model.addAttribute("message", "Your username or password is invalid.");

		if (logout != null)
			model.addAttribute("message", "Logged out successfully.");

		return "login";
	}
	
	@RequestMapping(value = "/admin/view-users", method = RequestMethod.GET)
	public String viewUsers(Model model) {
		model.addAttribute("users", userService.findAll());
		return "admin/view-users";
	}
}
