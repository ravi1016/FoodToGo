package com.example.demo.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.entity.Restaurant;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import com.example.demo.service.RestaurantService;

@Controller
public class AdminController {

	//public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	RestaurantService restaurantService;
	//=---------------------------------------(Restaurants)------------------------------
	@GetMapping("/admin/restaurants")
	public String getResturants(Model model) {
		model.addAttribute("restaurants", restaurantService.getAllRestaurants());
		return "restaurants";
	}
	
	@GetMapping("/admin/restaurants/add/{id}")
	public String addProductByRestaurantId(Model model,@PathVariable Integer id) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoryService.getAllCategoriesByRestaurantId(id));
		model.addAttribute("restaurants", restaurantService.getRestaurantById(id).get());
		return "productsAdd";
	}
	@GetMapping("/admin/restaurants/add")
	public String addRestaurant(Model model) {
		model.addAttribute("restaurant", new Restaurant());
		return "restaurantsAdd";
	}
	
	@PostMapping("/admin/restaurants/add")
	public String postRestaurnatAdd(@ModelAttribute("restaurant") Restaurant restaurant ) {
		restaurantService.addRestaurant(restaurant);
		return "redirect:/admin/restaurants";
	}
	
	@GetMapping("/admin/restaurants/delete/{id}")
	public String deleteRestaurant(@PathVariable int id) {
		restaurantService.deleteRestaurant(id);
		return "redirect:/admin/restaurants";
		
	}
	
	@GetMapping("/admin/restaurants/update/{id}")
	public String updateRestaurant(@PathVariable int id,Model model) {
		Optional<Restaurant> restaurant =restaurantService.getRestaurantById(id);
		if(restaurant.isPresent()) {
			model.addAttribute("restaurant", restaurant.get());
			return "restaurantsAdd";
		}else {
			return "404";
		}
	}
	//=---------------------------------------(Categories)------------------------------
	@GetMapping("/admin")
	public String adminHome() throws Exception {
		return "adminHome";
	}
	
	@GetMapping("/admin/categories")
	public String getCategories(Model model) {
		model.addAttribute("categories", categoryService.getAllCategories());
		return "categories";
	}
	
	@GetMapping("/admin/categories/add")
	public String addCategories(Model model) {
		model.addAttribute("category", new Category());
		model.addAttribute("restaurants", restaurantService.getAllRestaurants());
		return "categoriesAdd";
	}
	
	@PostMapping("/admin/categories/add")
	public String postCategoryAdd(@ModelAttribute("category") Category category ) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/delete/{id}")
	public String deleteCategory(@PathVariable Integer id) {
		categoryService.deleteCategory(id);
		return "redirect:/admin/categories";
	}
	
	@GetMapping("/admin/categories/update/{id}")
	public String updateCategory(@PathVariable int id,Model model) {
		Optional<Category> category =categoryService.getCategoryById(id);
		model.addAttribute("category", category.get());
		//model.addAttribute("restaurants", restaurantService.getRestaurantById(id).get());
		return "categoriesAdd";
		
	}
	
	@GetMapping("/admin/products")
	public String getProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	
	@GetMapping("/admin/products/add")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("restaurants", restaurantService.getAllRestaurants());
		return "productsAdd";
	}
	
	@PostMapping("/admin/products/add")
	public String postProductAdd(@ModelAttribute("product") Product product) throws IOException{
		productService.addProduct(product);
		return "redirect:/admin/products";
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return "redirect:/admin/products";
		
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProduct(@PathVariable Long id,Model model) {
		Product product = productService.getProductById(id).get();
		model.addAttribute("product", product);
		model.addAttribute("categories", categoryService.getAllCategories());
		model.addAttribute("restaurants", restaurantService.getAllRestaurants());
		
		return "productsAdd";
		
	}
	 
	
}
