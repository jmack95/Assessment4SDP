package com.assessment4.controllers;



import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.assessment4.entities.Merchandise;

import com.assessment4.services.MerchandiseService;


@Controller

public class MerchandiseController {
	
	 @Autowired
	 private MerchandiseService merchandiseService;

	 
	 @GetMapping("/addMerch")
	 public String merchForm( Model model) { 
		 model.addAttribute("merchandise", new Merchandise());
		 return "views/merchForm";
		 
	 }
	 
		@PostMapping("/addMerch")
	    public String addMerch(@Valid Merchandise merchandise, BindingResult bindingResult, Model model) {
			if(bindingResult.hasErrors()) {
				return "views/merchForm";
			}
			
			merchandiseService.createMerch(merchandise);
			
			return "views/merchlist";

		}
		
		@RequestMapping(method=RequestMethod.DELETE,value="/merchandise/{title}")
		public void deleteTheMerchandise(@PathVariable String title) {
			merchandiseService.deleteTheMerchandise(title);
		}
		@RequestMapping("/delete-merch")
		public String deleteTheMarker(@RequestParam String title,HttpServletRequest request) {
			merchandiseService.deleteTheMerchandise(title);
			request.setAttribute("merchandise", merchandiseService.showAllMerchandise());
			
			return "views/merchlist";
		}
	 
	 @GetMapping("/merchandise")
		public String listMerch(Model model, @RequestParam(defaultValue="")  String title) {
			model.addAttribute("merchandise", merchandiseService.findByTitle(title));
			return "views/merchlist";
		}
}
	
