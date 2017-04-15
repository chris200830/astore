package com.rx.powerstore.validator;

import com.rx.powerstore.entity.User;
import com.rx.powerstore.service.UserService;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;

		if (user.getUsername().length() < 3 || user.getUsername().length() > 16)
			errors.rejectValue("username", "Size.userForm.username");

		if (userService.findByUsername(user.getUsername()) != null)
			errors.rejectValue("username", "Duplicate.userForm.username");

		if (user.getPassword().length() < 6 || user.getPassword().length() > 32)
			errors.rejectValue("password", "Size.userForm.password");

		if (!user.getPasswordConfirm().equals(user.getPassword()))
			errors.rejectValue("password", "Different.userForm.password");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "NotEmpty");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "NotEmpty");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emailAddress", "NotEmpty");

		if (userService.findByEmailAddress(user.getEmailAddress()) != null)
			errors.rejectValue("emailAddress", "Duplicate.userForm.emailAddress");
		
		if(!EmailValidator.getInstance().isValid(user.getEmailAddress()))
			errors.rejectValue("emailAddress", "Invalid.userForm.emailAddress");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "telephoneNumber", "NotEmpty");
	}

}
