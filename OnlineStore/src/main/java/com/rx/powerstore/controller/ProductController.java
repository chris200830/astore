package com.rx.powerstore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.rx.powerstore.entity.Product;
import com.rx.powerstore.entity.Thumbnail;
import com.rx.powerstore.service.CategoryService;
import com.rx.powerstore.service.ProductService;
import com.rx.powerstore.service.ThumbnailService;
import com.rx.powerstore.validator.ProductValidator;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ThumbnailService thumbnailService;

	@Autowired
	private ProductValidator productValidator;

	@GetMapping("/admin/add-product")
	public String addProduct(Model model) {
		model.addAttribute("productForm", new Product());
		model.addAttribute("categories", categoryService.findAll());
		return "admin/add-product";
	}

	@PostMapping("/admin/add-product")
	public String addProduct(@Valid @ModelAttribute("productForm") Product productForm, BindingResult bindingResult,
			HttpServletRequest request, @RequestParam("thumbnailFile") MultipartFile multipart, Model model) {
		productValidator.validate(productForm, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", categoryService.findAll());
			return "admin/add-product";
		}
		Thumbnail thumbnail = thumbnailService.getNewThumbnail(multipart);

		if (thumbnail == null)
			return "admin/add-product";

		List<Thumbnail> thumbnails = new ArrayList<Thumbnail>();
		thumbnails.add(thumbnail);
		productForm.setThumbnails(thumbnails);
		productForm.setCategory(categoryService.findOne(Integer.parseInt(request.getParameter("categoryId"))));
		productService.save(productForm);

		return "redirect:/admin/view-products";
	}

	@GetMapping("/admin/view-products")
	public String viewProducts(Model model) {
		model.addAttribute("products", productService.findAll());
		return "admin/view-products";
	}

	@RequestMapping(value = "/admin/delete-product/id={id}", method = RequestMethod.GET)
	public String deleteProduct(@PathVariable("id") long id, Model model) {
		productService.delete(productService.findOne(id));

		return "redirect:/admin/view-products";
	}

	@GetMapping("/admin/update-product/id={id}")
	public String updateProduct(@PathVariable("id") long id, Model model) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("product", productService.findOne(id));
		return "admin/add-product";
	}

	@PostMapping("/admin/update-product/id={id}")
	public String updateProduct(@PathVariable("id") long id, @Valid @ModelAttribute("product") Product product,
			BindingResult bindingResult, HttpServletRequest request,
			@RequestParam("thumbnailFile") MultipartFile multipart) {

		List<Thumbnail> thumbnails = new ArrayList<Thumbnail>();
		Thumbnail thumbnail = thumbnailService.getNewThumbnail(multipart);
		thumbnails.add(thumbnail);

		product.setThumbnails(thumbnails);
		product.setCategory(categoryService.findOne(Integer.parseInt(request.getParameter("categoryId"))));
		productService.save(product);

		return "redirect:/admin/view-products";
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public String viewProduct(@PathVariable("id") long id, Model model) {
		model.addAttribute("product", productService.findOne(id));
		return "product";
	}

}
