package com.example.web;

import javax.mail.MessagingException;
import com.example.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.service.EmailService;
import com.example.service.UserService;
import com.example.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
private UserService userService;
@Autowired
private EmailService emailService;
public UserRegistrationController(UserService userService) {
	super();
	this.userService = userService;
}
 

@PostMapping
public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto )
{
	registrationDto.setPassword(PasswordGenerator.generateStrongPassword());
	userService.save(registrationDto);
    try {
        emailService.sendMail(registrationDto.getEmail(), "Confirmation de l'inscription", "Bonjour Voici votre mot de passe : " + registrationDto.getPassword());
    } catch (MessagingException e) {
        e.printStackTrace();
    }
	return "redirect:/registration?success";
	
}
@GetMapping
public String showRegistrationform(Model model)
{
	model.addAttribute("user", new UserRegistrationDto());
	return "registration";
}
}
