package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.model.User;
import com.example.service.EmailService;
import com.example.service.UserService;
import com.example.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/profile")
public class UserProfileController {
	private UserService userService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UserProfileController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PutMapping("/update")
	public String UpdateUserAccount(@ModelAttribute("user") User user) {

		/*
		 * try { emailService.sendMail(registrationDto.getEmail(),
		 * "Confirmation de l'inscription", "Bonjour Voici votre mot de passe : " +
		 * registrationDto.getPassword()); } catch (MessagingException e) {
		 * e.printStackTrace(); }
		 */
		return "redirect:/profile/";

	}

	@GetMapping("/monprofile")
	public String showProfile(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String email;
		if (principal instanceof UserDetails) {
			email = ((UserDetails) principal).getUsername();

		} else {
			email = principal.toString();
		}
		User user = userService.findbymail(email);
		System.out.println(user.getEmail());
		model.addAttribute("user", user);
		return "Profile";
	}

	@GetMapping("/edit/{email}")
	public String showProfileform(@PathVariable("email") String email, Model model) {
		System.out.println(email);
		User user = userService.findbymail(email);
		model.addAttribute("user", user);
		return "editprofile";
	}

}
