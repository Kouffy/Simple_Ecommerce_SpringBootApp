package com.example.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Rating;
import com.example.repository.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService{
	@Autowired
	RatingRepository ratingRepository;
	
	@Override
	public Rating saveRating(Rating rating) {
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getRating() {
		return (List<Rating>) ratingRepository.findAll();
	}

}
