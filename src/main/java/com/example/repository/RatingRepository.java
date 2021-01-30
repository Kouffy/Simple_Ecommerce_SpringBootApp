package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Rating;

public interface RatingRepository extends CrudRepository<Rating, Long>{

}
