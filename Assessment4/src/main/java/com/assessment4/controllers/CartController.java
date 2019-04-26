package com.assessment4.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.assessment4.entities.Cart;
import com.assessment4.entities.CartMerchandise;
import com.assessment4.entities.Merchandise;
import com.assessment4.services.CartMerchandiseService;
import com.assessment4.services.CartService;
import com.assessment4.services.MerchandiseService;
import com.assessment4.entities.User;
import com.assessment4.repositories.MerchandiseRepository;
import com.assessment4.repositories.UserRepository;
import com.assessment4.services.UserService;

@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private UserService userService;
	@Autowired
	private MerchandiseService merchandiseService;
	@Autowired
	private MerchandiseRepository merchandiseRepository;

	@Autowired
	private CartMerchandiseService cartMerchandiseService;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/addToCart")
	public String addToCart(Model model, @RequestParam("id") int id, @RequestParam(defaultValue = "") String title) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findOne(auth.getName());
		Cart cart = cartService.findByUserEmail(user.getEmail());
		Merchandise merchandise = merchandiseService.findById(id);
		
		

		ArrayList<CartMerchandise> cartMerchandise = new ArrayList<CartMerchandise>();
		cartMerchandise.addAll(cart.getCartMerchandise());
		boolean exist = true;

		for (int i = 0; i < cartMerchandise.size(); i++) {
		CartMerchandise theMerch = cartMerchandise.get(i);
		if (theMerch.getMerchandise() == merchandise) {

		int temp = cartMerchandise.get(i).getAmount();
		cartMerchandise.get(i).setAmount(temp+1);
		cartMerchandiseService.saveCartMerchandise(cartMerchandise.get(i));
		Set<CartMerchandise> updatedList = new HashSet<>(cartMerchandise);
		cart.setCartMerchandise(updatedList);
		exist = false;
		}
		}

		if (exist) {
			CartMerchandise cartM = new CartMerchandise(cart, merchandise, 1);
			cartMerchandiseService.saveCartMerchandise(cartM);
		cartMerchandise.add(cartM);

		Set<CartMerchandise> updatedList = new HashSet<>(cartMerchandise);

		cart.setCartMerchandise(updatedList);
		} 
		cartService.saveCart(cart);
		List<Merchandise> merch = merchandiseService.findByTitleLike(title);
		model.addAttribute("merchandise", merch);
		

		return "views/successCart";
	}
	
	@GetMapping("/myCart")
	public String viewCart(Model model , Principal principal) {
	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	String email = principal.getName();
	User user = userService.findOne(email);
	model.addAttribute("name", user.getName());
	Cart cart = cartService.findByUserEmail(user.getEmail());
	ArrayList<CartMerchandise> cm = new ArrayList<CartMerchandise>();
	cm.addAll(cart.getCartMerchandise());
	model.addAttribute("cart", cart);
	model.addAttribute("cartMerchandise", cm);

	double cartMerchTotal = 0;
	for (int i = 0; i < cm.size(); i++) {
	CartMerchandise cartMerchandise = cm.get(i);
	Merchandise merchandise = merchandiseService.findById(cartMerchandise.getMerchandise().getId());
	cartMerchTotal = cartMerchTotal + (merchandise.getPrice() * cartMerchandise.getAmount());
	}
	
	model.addAttribute("cmtotal", cartMerchTotal);

	return "views/myCart";
	}
	

}
