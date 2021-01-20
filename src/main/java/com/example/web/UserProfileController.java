package com.example.web;

import java.util.List;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.EmailService;
import com.example.service.UserService;

@Controller
@RequestMapping("/profile")
public class UserProfileController {
	private UserService userService;
	@Autowired
	private EmailService emailService;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

	public UserProfileController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@PostMapping("/update")
	public String UpdateUserAccount(@ModelAttribute("user") User user,
			@RequestParam(value = "newpass", required = true) String newpass,
			@RequestParam(value = "oldpass", required = true) String oldpass) {
		if (passwordEncoder.matches(oldpass, user.getPassword())) {
			User userfromdb = userService.findbymail(user.getEmail());
			userfromdb.setFirstName(user.getFirstName());
			userfromdb.setEmail(user.getEmail());
			userfromdb.setLastName(user.getLastName());
			userfromdb.setLogin(user.getLogin());
			userfromdb.setPassword(passwordEncoder.encode(newpass));
			userfromdb.setVille(user.getVille());
			userfromdb.setTel(user.getTel());
			userService.updateUser(userfromdb);
			try {
				emailService.sendMail(user.getEmail(), "Confirmation de la modification de profile",
						"Bonjour Voici votre mot de passe : " + newpass);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return "redirect:/profile/monprofile";
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
		model.addAttribute("user", user);
		return "Profile";
	}

	@GetMapping("/edit/{email}")
	public String showProfileform(@PathVariable("email") String email, Model model) {
		User user = userService.findbymail(email);
		model.addAttribute("user", user);
		return "editprofile";
	}

	
	@RequestMapping(value = "/validemail", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Boolean validateEmail(@RequestParam(value = "email", required = true) String email) {
		User us = userService.findbymail(email);
		return us == null ? true : false;
	}

	@RequestMapping(value = "/validlog", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Boolean validateUsername(@RequestParam(value = "login", required = true)  String login) {
		User user = null;
		List<User> users = userRepository.findAll();
		for (User use : users) {
			if (use.getLogin().equals(login)) {
				user = use;
				break;
			}

		}
		return user == null ? true : false;
	}

}
