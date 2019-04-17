package com.assessment4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.assessment4.entities.User;
import com.assessment4.services.UserService;

@SpringBootApplication

public class OnlineShopApplication  implements  CommandLineRunner{
	   @Autowired
	   private UserService userService;
	     
	public static void main(String[] args) {
		SpringApplication.run(OnlineShopApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		  {
    		  User newAdmin = new User("admin@gmail.com", "John","Tud-Aungier Street", "qqqqqqqq");
    		  userService.createAdmin(newAdmin); 
    		
    	  }
	}
}
