package com.example.demo.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private double price;
	private double weight;
	private String description;
	private String imageName;
	private Integer categoryId;
	private Integer restaurantId;
	
	@ManyToOne
	@JoinColumn(name = "categoryId", referencedColumnName = "categoryId" , insertable = false, updatable = false)
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "restaurantId", referencedColumnName = "restaurantId" , insertable = false, updatable = false)
	private Restaurant restaurant;
	
	

	
}
