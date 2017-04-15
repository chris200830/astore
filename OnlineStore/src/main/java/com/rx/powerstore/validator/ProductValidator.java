package com.rx.powerstore.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.rx.powerstore.entity.Product;
import com.rx.powerstore.service.ProductService;

@Component
public class ProductValidator implements Validator {
	@Autowired
	private ProductService productService;

	@Override
	public boolean supports(Class<?> aClass) {
		return Product.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		Product product = (Product) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty");

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "totalCount", "NotEmpty");

		if (productService.findByName(product.getName()) != null)
			errors.rejectValue("name", "Duplicate.productForm.name");

		if (product.getPrice() < 0)
			errors.rejectValue("price", "Negative.productForm.price");

		if (product.getTotalCount() < 0)
			errors.rejectValue("totalCount", "Negative.productForm.totalCount");
	}
}
