package com.abhi.training.demo.model;

public record Product(
	 String image,
	 double price,
	 Rating rating,
	 String description,
	 int id,
	 String title,
	 String category) {}
