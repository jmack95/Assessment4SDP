package com.assessment4.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.assessment4.entities.User;
import com.assessment4.services.UserService;

@Controller
public class AdminRegisterController {
	@Autowired
	private UserService userService;

	@GetMapping("/registerAdmin")
	public String registerForm(Model model) {

		model.addAttribute("user", new User());
		return "views/adminregisterForm";
	}
	
	
	@PostMapping("/registerAdmin")
    public String registerAdmin(@Valid User user, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "views/adminregisterForm";
		}
		if(userService.isUserPresent(user.getEmail())) {
			model.addAttribute("exist",true);

			return "views/adminregisterForm";

		}
		userService.createAdmin(user);
		
		return "views/successAdmin";

	}

}
