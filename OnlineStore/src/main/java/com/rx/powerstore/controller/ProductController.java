package com.rx.powerstore.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ThumbnailService thumbnailService;

	@GetMapping("/add-product")
	public String addProduct(Model model) {
		model.addAttribute("prductForm", new Product());
		model.addAttribute("categories", categoryService.findAll());
		return "admin/add-product";
	}

	@PostMapping("/add-product")
	public String addProduct(@ModelAttribute("productForm") Product productForm, HttpServletRequest request,
			@RequestParam("thumbnailFile") MultipartFile thumbnailFile) {
		thumbnailService.copyToDisk(thumbnailFile);
		Thumbnail thumbnail = new Thumbnail();
		thumbnail.setFilePath(thumbnailService.getFilePath());
		thumbnailService.save(thumbnail);
		List<Thumbnail> thumbnails = new ArrayList<Thumbnail>();
		thumbnails.add(thumbnail);
		productForm.setThumbnails(thumbnails);
		productForm.setCategory(categoryService.findOne(Integer.parseInt(request.getParameter("categoryId"))));
		productService.save(productForm);
		return "admin/view-products";
	}

	@GetMapping("/view-products")
	public String viewProducts(Model model) {
		model.addAttribute("products", productService.findAll());
		return "admin/view-products";
	}
	
	@RequestMapping(value="/product/{id}", method=RequestMethod.GET)
	public String viewProduct(@PathVariable("id") long id, Model model) {
		model.addAttribute("product", productService.findOne(id));
		return "product";
	}
}
