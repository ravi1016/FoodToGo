package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Product;
import com.example.demo.global.GlobalData;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;

@Controller
public class CartController {
	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/addToCart/{id}")
	public String addToCart(Model model,@PathVariable Long id) {
		Product product = productService.getProductById(id).get();
		Integer restaurantId = product.getRestaurantId();
		GlobalData.cart.add(productService.getProductById(id).get());
		model.addAttribute("products", productService.getAllProductsByRestaurantId(restaurantId));
		model.addAttribute("categories", categoryService.getAllCategoriesByRestaurantId(restaurantId));
		model.addAttribute("cartCount", GlobalData.cart.size());
		
		return "shop";
	}
	
	@GetMapping("/cart")
	public String cart(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart", GlobalData.cart);
		return "cart";
	}
	
	@GetMapping("/cart/removeItem/{index}")
	public String cartItemRemove(@PathVariable int index) {
		GlobalData.cart.remove(index);
		return "redirect:/cart";
	}
	
	@GetMapping("/checkout")
	public String chechout(Model model) {
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		return "checkout";
	}
	
	
}
