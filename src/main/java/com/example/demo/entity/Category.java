package com.example.demo.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Transactional 
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "categoryId")
	private Integer id;
	
	private String name;
	
	private Integer restaurantId;
	
	@ManyToOne
	@JoinColumn(name = "restaurantId", referencedColumnName = "restaurantId" , insertable = false, updatable = false)
	private Restaurant restaurant;
	
	
	
	
	
	
	
	

}
