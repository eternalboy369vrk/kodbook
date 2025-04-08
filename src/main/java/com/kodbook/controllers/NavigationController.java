package com.kodbook.controllers;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.kodbook.entities.Notification;
import com.kodbook.entities.Post;
import com.kodbook.entities.User;
import com.kodbook.services.NotificationService;
import com.kodbook.services.PostService;
import com.kodbook.services.UserService;

import jakarta.servlet.http.HttpSession;



@Controller
public class NavigationController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	UserService service;
	
	@Autowired
	NotificationService notservice;
	
	
	//for index page
	@GetMapping("/")
	public String index()
	{
		return "index";
		
	}
	//for sign-up page
	@GetMapping("/openSignUp")
	public String openSignUp()
	{
		return "signUp";
		
	}
	//for opening createpost page
	@GetMapping("/openCreatePost")
	public String openCreatePost(HttpSession session)
	{	
		if(session.getAttribute("username")!=null)
		{
		return "createPost";
		}
	 else
		{
			return "index";
		}
	}
	
	//for rediecting to home page
	@GetMapping("/goHome")
	public String login(Model model,HttpSession session)	{
			List<Post> allPosts = postService.fetchAllPosts();
			model.addAttribute("allPosts", allPosts);
			
			 
			 if(session.getAttribute("username")!=null)
				{
				 model.addAttribute("message", "Welcome ");		 
			return "home";
				}
			 else
				{
					return "index";
				}
	}
	
	//this is for when user clicks on profile in the posts it re directs to the partucular user
	@GetMapping("/openMyProfilee")
	public String openMyProfilee(@RequestParam("username") String username, HttpSession session, Model model) {
		
		
		User user = service.getUserByUsername(username);
		   List<Post> posts = user.getPosts();
		   model.addAttribute("posts", posts);
	    model.addAttribute("user", user);
	    return "myProfile";
	}
	
	

	//for opening my profile page
	@GetMapping("/openMyProfile")
	public String openMyProfile(HttpSession session, Model model) {
	    String username = (String) session.getAttribute("username");
	    User user = service.getUserByUsername(username);
	    List<Post> posts = user.getPosts();
	    model.addAttribute("user", user);
	    model.addAttribute("posts", posts);
	    
	    if(session.getAttribute("username")!=null)
		{
	    return "myProfile";
		}
	    else
		{
			return "index";
		}
	}
	

	//for opening edit profile page
	@GetMapping("/openEditProfile")
	public String openEditProfile(HttpSession session)
	{
		if(session.getAttribute("username")!=null)
		{
			return "editProfile";
		}
		else
		{
			return "index";
		}
		
	}
	
	
	
	//for Delecte account
	@GetMapping("/deleteAccount")
	public String deleteAccount() {
	   
	    return "deleteAccount";
	}
	

	
	
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model)
	{
		session.invalidate();
		return "index";
	}
	
	
	
	
	//for notifications
	
	  @GetMapping("/openNotification")
	    public String notifications(HttpSession session, Model model) {
	        String username = (String) session.getAttribute("username");
	        User user = service.getUserByUsername(username);
	        List<Notification> notifications = notservice.getNotifications(user);
	        model.addAttribute("notifications", notifications);
	        return "notifications";
	    }
	 

	 
	  
	  @GetMapping("/openResetPassword")
	  public String openResetPassword()
	  {
		return "forgotPassword";
		  
	  }
	 
	  @GetMapping("/reset-password")
	  public String resetpassword()
	  {
		return "resetPassword";
		  
	  }
	  
	  @GetMapping("/openEditPost")
	  public String openEditPost()
	  {
		  return "editPost";
		  
	  }
	  
	

	
}
