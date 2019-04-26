package com.assessment4.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.assessment4.entities.Cart;
import com.assessment4.entities.User;
import com.assessment4.services.CartService;
import com.assessment4.services.UserService;

@Controller
public class RegisterController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;

	@GetMapping("/register")
	public String registerForm(Model model) {

		model.addAttribute("user", new User());
		return "views/registerForm";
	}
	
	
	@PostMapping("/register")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			return "views/registerForm";
		}
		if(userService.isUserPresent(user.getEmail())) {
			model.addAttribute("exist",true);

			return "views/registerForm";

		}
		userService.createUser(user);
		Cart cart = Cart.getInstance(user);
		cart.setUser(user);
		cartService.saveCart(cart);// cart created when user registers
		
		return "views/loginForm";

	}

}
