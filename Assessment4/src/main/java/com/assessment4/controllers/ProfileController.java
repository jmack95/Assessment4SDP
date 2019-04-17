package com.assessment4.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.assessment4.entities.User;
import com.assessment4.services.UserService;

@Controller
public class ProfileController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/profile")
	public String showProfilePage(Model model, Principal principal) {
		
		String email = principal.getName();
		User user = userService.findOne(email);
		model.addAttribute("name", user.getName());
		
		
		
		return "views/profile";
	}

}
