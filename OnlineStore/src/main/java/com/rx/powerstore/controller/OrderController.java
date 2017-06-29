package com.rx.powerstore.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rx.powerstore.entity.OrderProduct;

import com.rx.powerstore.service.ProductService;
import com.rx.powerstore.service.UserService;
import com.rx.powerstore.basket.Basket;

@Controller
public class OrderController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add-to-cart{id}")
	public String addToBasket(@RequestParam("id") long id, HttpServletRequest request, Model model) {
		OrderProduct orderProduct = new OrderProduct();
		
		Integer count = Integer.parseInt(request.getParameter("count"));
		orderProduct.setProduct(productService.findOne(id));
		orderProduct.setCount(count);
		//float totalPrice = count * productService.findOne(id).getPrice();
		Basket.getInstance().getItems().add(orderProduct);
		return "redirect:/";
	}
	
	@GetMapping("/cart-items")
	public String cartItems(Model model) {
		model.addAttribute("items", Basket.getInstance().getItems());
		model.addAttribute("totalPrice", Basket.getInstance().calculateTotalPrice());
		return "cart";
	}
	
	@GetMapping("/subscriber/delivery-address")
	public String deliveryAddress(Model model, Principal principal) {
		System.out.println(principal.getName());
	//	model.addAttribute("addressList", userService.findByUsername(principal.getName()).getAddresses());
		model.addAttribute("totalPrice", Basket.getInstance().calculateTotalPrice());
		return"subscriber/view-addresses";
	}
}
