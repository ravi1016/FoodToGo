package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findAllByCategoryId(int id);
	List<Product> findAllByRestaurantId(int id);
	List<Product> findAllByNameIgnoreCase(String name);

}
