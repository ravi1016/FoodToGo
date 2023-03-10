package com.example.demo.repo;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{
}
