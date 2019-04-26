package com.assessment4.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.assessment4.entities.Cart;
import com.assessment4.entities.CartMerchandise;
import com.assessment4.entities.MasterCard;
import com.assessment4.entities.Merchandise;
import com.assessment4.services.CartMerchandiseService;
import com.assessment4.services.CartService;
import com.assessment4.services.MerchandiseService;
import com.assessment4.services.OrderService;
import com.assessment4.services.PurchaseServiceFacade;
import com.assessment4.services.PurchaseServiceFacadeImpl;
import com.assessment4.entities.User;
import com.assessment4.entities.UserOrder;
import com.assessment4.entities.Visa;
import com.assessment4.repositories.MerchandiseRepository;
import com.assessment4.repositories.UserRepository;
import com.assessment4.services.UserService;

@Controller
public class OrderController {
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
	private OrderService orderService;

	@GetMapping("/order")
	public String order(Model model,Principal principal, HttpServletRequest request) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			String email = principal.getName();
			User user = userService.findOne(email);
			model.addAttribute("name", user.getName());
			Cart cart = cartService.findByUserEmail(user.getEmail());
			ArrayList<CartMerchandise> cm = new ArrayList<CartMerchandise>();
			cm.addAll(cart.getCartMerchandise());
			model.addAttribute("cart", cart);

			double cartMerchTotal = 0;
			for (int i = 0; i < cm.size(); i++) {
			CartMerchandise cartMerchandise = cm.get(i);
			Merchandise merchandise = merchandiseService.findById(cartMerchandise.getMerchandise().getId());
			cartMerchTotal = cartMerchTotal + (merchandise.getPrice() * cartMerchandise.getAmount());
			}

			
			String name = request.getParameter("name");
			model.addAttribute("cartMerchandise", cm);
			model.addAttribute("cmTotal", cartMerchTotal);


		 return "views/order";
		
}


    @PostMapping("/order")
    public String order(@Valid @ModelAttribute("userOrder") UserOrder userOrder, @RequestParam("cmTotal") double total, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findOne(auth.getName());
        Cart cart = cartService.findByUserEmail(user.getEmail());
        Set<Merchandise> products = new HashSet<>();
        

        ArrayList<CartMerchandise> cartMerch = new ArrayList<CartMerchandise>();
        cartMerch.addAll(cart.getCartMerchandise());

        PurchaseServiceFacade purchaseServiceFacade = new PurchaseServiceFacadeImpl();

        if (purchaseServiceFacade.placeOrder(cartMerch)) {

        	products.addAll(products);

            UserOrder order = new UserOrder();
            orderService.saveOrder(order);

            if (request.getParameter("payment_method").equals("Visa")) {
                Visa visa = new Visa(request.getParameter("name"), request.getParameter("cardNumber"), request.getParameter("expiry"));

                if (order.pay(visa, cart)){
                    orderService.saveOrder(order);
                    cartMerchandiseService.emptyCart(cartMerchandiseService.findByCartId(cart.getId()));

                  
                    return "views/success";
                }
                else {
                    String visaError = "";
                    model.addObject("cmTotal", total);
                  
                    return "views/order";
                }
            } else if (request.getParameter("payment_method").equals("Mastercard")) {
                MasterCard mastercard = new MasterCard(request.getParameter("name"), request.getParameter("cardNumber"), request.getParameter("expires"));

                if (order.pay(mastercard, cart)) {
                    orderService.saveOrder(order);
                    cartMerchandiseService.emptyCart(cartMerchandiseService.findByCartId(cart.getId()));

                   
                }
                else {
                    
                    model.addObject("cmTotal", total);
                    return "views/order";
                }
            }
        }
        else {
           
            model.setViewName("order");
            model.addObject("cmTotal", total);
        }

		 return "views/order";
    }
}

