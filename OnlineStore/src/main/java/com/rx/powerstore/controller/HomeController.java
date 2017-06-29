package com.rx.powerstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rx.powerstore.basket.Basket;

import com.rx.powerstore.service.ProductService;

@Controller
public class HomeController {
	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public String home(Model model, HttpServletRequest request) {
		// System.out.println(productService.findOne((long)7).getThumbnailPath());'

		model.addAttribute("running", productService.findByCategoryName("Running Shoes"));
		model.addAttribute("sneakers", productService.findByCategoryName("Sneakers"));
		model.addAttribute("more", productService.findAll());
		model.addAttribute("totalPrice", Basket.getInstance().calculateTotalPrice());
		// request.getSession().setAttribute("totalPrice",
		// Basket.getInstance().calculateTotalPrice());
		return "index";
	}

	@GetMapping("/orderby{price}")
	public String orderByPrice(Model model, @RequestParam("price") String price) {
		switch (price) {
		case "asc":
			model.addAttribute("running", productService.findByCategoryNameOrderByPriceAsc("Running Shoes"));
			model.addAttribute("sneakers", productService.findByCategoryNameOrderByPriceAsc("Sneakers"));
			model.addAttribute("more", productService.findAll());
			break;
		case "desc":
			model.addAttribute("running", productService.findByCategoryNameOrderByPriceDesc("Running Shoes"));
			model.addAttribute("sneakers", productService.findByCategoryNameOrderByPriceDesc("Sneakers"));
			model.addAttribute("more", productService.findAll());
			break;
		default:
			model.addAttribute("more", productService.findAll());
		}
		model.addAttribute("totalPrice", Basket.getInstance().calculateTotalPrice());
		return "index";
	}
	
	@GetMapping("/sortby{manufacturer}")
	public String orderByManufacturer(Model model, @RequestParam("manufacturer") String manufacturer) {
		switch(manufacturer) {
		case "Nike":
			model.addAttribute("running", productService.findByCategoryNameAndManufacturer("Running Shoes", manufacturer));
			model.addAttribute("sneakers", productService.findByCategoryNameAndManufacturer("Sneakers", manufacturer));
			model.addAttribute("more", productService.findAll());
			break;
		case "Adidas":
			model.addAttribute("running", productService.findByCategoryNameAndManufacturer("Running Shoes", manufacturer));
			model.addAttribute("sneakers", productService.findByCategoryNameAndManufacturer("Sneakers", manufacturer));
			model.addAttribute("more", productService.findAll());
			default:
				model.addAttribute("Running Shoes", productService.findAll());
		}
		model.addAttribute("totalPrice", Basket.getInstance().calculateTotalPrice());
		return "index";
	}
}
