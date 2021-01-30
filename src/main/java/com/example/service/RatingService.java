package com.example.service;

import java.util.List;
import com.example.model.Rating;


public interface RatingService {
	Rating saveRating(Rating rating);
	List<Rating> getRating();
}
