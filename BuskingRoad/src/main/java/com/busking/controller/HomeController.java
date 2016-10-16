package com.busking.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.UserDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserDao dao;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/{email}/{name}", method = RequestMethod.GET)
	public String home(Locale locale, Model model ,@PathVariable("email") String email,@PathVariable("name") String name) {
		System.out.println(email+""+name);
		System.out.println("GET input");
		return "home";
	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String Gethome(Locale locale, Model model) {
		
		System.out.println("Get input");
		
		return "home";
	}
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String Posthome(Locale locale, Model model) {
		
		System.out.println("Post input");
		
		return "home";
	}
}
