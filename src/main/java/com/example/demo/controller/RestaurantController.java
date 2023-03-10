package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.global.GlobalData;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.RestaurantService;

@Controller
public class RestaurantController {

	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	RestaurantService restaurantService;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "index";
	}
	
	@PostMapping("/search")
	public String searchByProductName(Model model,@RequestParam("search") String search) {
		model.addAttribute("products", productService.getAllProductsByName(search));
		return "shop";	
	}
	@GetMapping("/restaurant")
	public String restaurant(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("restaurants", restaurantService.getAllRestaurants());
		return "showRestaurants";
	}
	@GetMapping("/restaurant/{id}")
	public String restaurantCategories(Model model,@PathVariable int id){
		model.addAttribute("products", productService.getAllProductsByRestaurantId(id));
		model.addAttribute("categories", categoryService.getAllCategoriesByRestaurantId(id));
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
	}
	
	@GetMapping("/restaurant/category/{id}")
	public String shopByCategory(Model model,@PathVariable int id) {
		Category category = categoryService.getCategoryById(id).get();
		Integer restaurantId = category.getRestaurantId();
		model.addAttribute("products", productService.getAllProductsByCategoryId(id));
		model.addAttribute("categories", categoryService.getAllCategoriesByRestaurantId(restaurantId));
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(Model model,@PathVariable Long id) {
		model.addAttribute("product", productService.getProductById(id).get());
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "viewProduct";
	}
}
