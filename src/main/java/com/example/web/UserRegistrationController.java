package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.UserService;
import com.example.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
private UserService userService;

public UserRegistrationController(UserService userService) {
	super();
	this.userService = userService;
}
 

@PostMapping
public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto )
{
	userService.save(registrationDto);
	return "redirect:/registration?success";
	
}
@GetMapping
public String showRegistrationform(Model model)
{
	model.addAttribute("user", new UserRegistrationDto());
	return "registration";
}
}
