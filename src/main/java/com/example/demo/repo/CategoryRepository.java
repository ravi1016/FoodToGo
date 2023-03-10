package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;

public interface CategoryRepository extends JpaRepository<Category, Integer>{

	List<Category> findAllByRestaurantId(Integer id);
}
