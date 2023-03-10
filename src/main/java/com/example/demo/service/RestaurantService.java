package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Restaurant;
import com.example.demo.repo.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	public List<Restaurant> getAllRestaurants(){
		return restaurantRepository.findAll();
	}
	public void addRestaurant(Restaurant restaurant) {
		restaurantRepository.save(restaurant);
	}
	public void deleteRestaurant(int id) {
		restaurantRepository.deleteById(id);
	}
	public Optional<Restaurant> getRestaurantById(int id) {
		return restaurantRepository.findById(id);
	}
}
