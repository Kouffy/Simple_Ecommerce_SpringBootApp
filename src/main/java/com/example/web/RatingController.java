package com.example.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Rating;
import com.example.service.RatingService;

@Controller
@RequestMapping("/rating")
public class RatingController {
@Autowired
RatingService ratingService;

@GetMapping("/add")
public String showRatingform() {
	return "Rating";
}
@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
@ResponseBody
public String UpdateUserRating(@ModelAttribute("rating") Rating rating) {
	ratingService.saveRating(rating);
	return "redirect:/profile/monprofile";
}
}
