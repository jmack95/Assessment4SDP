package com.assessment4.controllers;

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

		ArrayList<CartMerchandise> cart_merchandise = new ArrayList<CartMerchandise>();
	
		cart_merchandise.addAll(cart.getCartMerchandise());
		boolean exist = true;

		for (int i = 0; i < cart_merchandise.size(); i++) {
		CartMerchandise theMerch = cart_merchandise.get(i);
		if (theMerch.getMerchandise() == merchandise) {

		int temp = cart_merchandise.get(i).getAmount();
		cart_merchandise.get(i).setAmount(temp+1);

		cartMerchandiseService.saveCartMerchandise(cart_merchandise.get(i));
		Set<CartMerchandise> updatedList = new HashSet<>(cart_merchandise);
		cart.setCartMerchandise(updatedList);
		exist = false;
		}
		}

		if (exist) {
			CartMerchandise cartMerchandise = new CartMerchandise(cart, merchandise, 1);
			cartMerchandiseService.saveCartMerchandise(cartMerchandise);
		cart_merchandise.add(cartMerchandise);

		Set<CartMerchandise> updatedList = new HashSet<>(cart_merchandise);

		cart.setCartMerchandise(updatedList);
		}

		cartService.saveCart(cart);

		String successMessage = "";
		model.addAttribute("successMessage", successMessage);

		List<Merchandise> merch = merchandiseService.findByTitleLike(title);
		model.addAttribute("merchandise", merch);
		

		return "views/itemList";
	}
	
	 @GetMapping("/viewCart")
	 public String viewCart(Model model) {
	 Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 User user = userService.findOne(auth.getName());
	 Cart cart = user.getCart();
	 // Set <Item> items = cart.getItems();
	 List<CartMerchandise> cartItems = new ArrayList<>();
	 cartItems.addAll(cart.getCartMerchandise());
	 model.addAttribute("cartItems", cartItems);
	 model.addAttribute("name", user.getName());
	 double price = cart.calculateTotal();
	 model.addAttribute("total", price);
	 model.addAttribute("cart", cart);
	 return "views/viewCart";
	 }

}
