package com.example.spring_security_jsp_user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommonController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String getHomepage(Model model) {
		if(SecurityContextHolder.getContext().getAuthentication().getName() != null) {
			model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
			System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
		}
		return "index";
	}
	
	@GetMapping("/signin")
	public String getSigninPage() {
		return "signin";
	}
	
	@GetMapping("/signup")
	public String getSignupPage() {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String processSignup(@ModelAttribute("user") User user, Model model) {
		System.err.println("username: " + user.getUsername());
		if(userService.userExists(user.getUsername())) {
			model.addAttribute("errmsg", "User/Email already exists! Please try again.");
			return "signup";
		} else {
			userService.createNewUser(user);
			return "redirect:/";
		}
	}
	@GetMapping("admin/1")
	public String checkUser() {
		return "admin";
	}
	@GetMapping("user/1")
	@PreAuthorize("hasAuthority('USER')")
	public String checkUserAdmin() {
		return "user";
	}
}
