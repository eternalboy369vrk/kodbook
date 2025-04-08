package com.kodbook.controllers;


import org.springframework.mail.SimpleMailMessage;

import org.springframework.mail.javamail.JavaMailSender;
import java.io.IOException;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kodbook.entities.PasswordResetToken;
import com.kodbook.entities.Post;
import com.kodbook.entities.User;
import com.kodbook.repositories.UserRepository;
import com.kodbook.services.PostService;
import com.kodbook.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserService service;
	
	@Autowired
	PostService postService;
	
	@Autowired
	UserRepository repo;
	
	@Autowired
    private JavaMailSender emailSender;

	

	
	@PostMapping("/signUp")
	public String addUser(@ModelAttribute User user,Model model,@RequestParam String username,
			@RequestParam String password,@RequestParam String email)
	{
		
		if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
	        model.addAttribute("msg", "Please fill all the Details");
	        return "redirect:/signUp";
	    }
		//user exists?
		 username=user.getUsername();
		 email=user.getEmail();
		boolean status=service.userExists(username,email);
		if(status==false)
		{
			model.addAttribute("msg", "Registration is Sucessfull Please Login");
			service.addUser(user);
		}
		else
		{
			if(username != null && service.usernameExists(username))
			{
				model.addAttribute("msg", "Username is Already Exists Please Login...!");
			}
			else if(email != null && service.emailExists(email))
			{
				model.addAttribute("msg", "Email is Already Exists Please Login...!");
			}
		}
		
		return "index";
	}
	
	
	
	@GetMapping("/signUp")
	public String signUpForm(Model model) {
		model.addAttribute("msg", "Please entered Required Fields");
	    return "signUp"; 
	}
	
	



	
	
//	for login ----
	
	 @PostMapping("/login")
	    public String login(@RequestParam String username, @RequestParam String password,
	            HttpSession session, Model model) {
	        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
	            model.addAttribute("msg", "Please enter your username and password.");
	            return "index";
	        }
	        boolean status = service.validateUser(username, password);
	        if (status == true) {
	            User user = service.getUserByUsername(username);

	            if (user != null) {
	                
	                byte[] photo = user.getProfilePic();
	                if (photo != null) {
	                    String photoBase64 = Base64.getEncoder().encodeToString(photo);
	                    session.setAttribute("photoBase64", photoBase64);
	                } else {
	                    // Handle the case where photo is null
	                    // You could set a default profile picture here
	                    session.setAttribute("photoBase64", "default-profile-picture");
	                }
	                
	                session.setAttribute("username", username);
	                List<Post> allPosts = postService.fetchAllPosts();
	               
	                model.addAttribute("session", session);
	                model.addAttribute("allPosts", allPosts);
	                return "home";
	            } else {
	                model.addAttribute("msg", "User not found");
	                return "index";
	            }
	        } else {
	            User user = service.getUserByUsername(username);
	            if (user == null) {
	                model.addAttribute("msg", "User not exists please register With Us");
	            } else {
	                model.addAttribute("msg", "Hello User Your Credentials didnt match Please try again!....");
	            }
	            return "index";
	        }
	    }
	
//updating the photo immediatelu
	 
	 @PostMapping("/updateProfile")
	 public String updateProfile(@RequestParam(defaultValue = "") String gender, 
	                             @RequestParam String dob,
	                             @RequestParam String city,
	                             @RequestParam String bio,
	                             @RequestParam String college,
	                             @RequestParam String linkedIn,
	                             @RequestParam String gitHub,
	                             @RequestParam MultipartFile profilePic,
	                             HttpSession session,
	                             Model model) {
	     String username = (String) session.getAttribute("username");
	     User user = service.getUserByUsername(username);

	     // Retrieve existing user data from database
	     User existingUser = service.getUserByUsername(username);

	     // Update only the fields that have been changed
	     if (gender!=null&&!gender.isEmpty()) {
	         user.setGender(gender);
	     } else {
	         user.setGender(existingUser.getGender());
	     }

	     if (!dob.isEmpty()) {
	         user.setDob(dob);
	     } else {
	         user.setDob(existingUser.getDob());
	     }

	     if (!city.isEmpty()) {
	         user.setCity(city);
	     } else {
	         user.setCity(existingUser.getCity());
	     }

	     if (!bio.isEmpty()) {
	         user.setBio(bio);
	     } else {
	         user.setBio(existingUser.getBio());
	     }

	     if (!college.isEmpty()) {
	         user.setCollege(college);
	     } else {
	         user.setCollege(existingUser.getCollege());
	     }

	     if (!linkedIn.isEmpty()) {
	         user.setLinkedIn(linkedIn);
	     } else {
	         user.setLinkedIn(existingUser.getLinkedIn());
	     }

	     if (!gitHub.isEmpty()) {
	         user.setGitHub(gitHub);
	     } else {
	         user.setGitHub(existingUser.getGitHub());
	     }
	     System.out.println("Profile Pic: " + profilePic);
	     if (profilePic != null && !profilePic.isEmpty()) {
	         try {
	             user.setProfilePic(profilePic.getBytes());
	             // Update the session attribute with the new profile picture
	             byte[] photo = user.getProfilePic();
	             String photoBase64 = Base64.getEncoder().encodeToString(photo);
	             session.setAttribute("photoBase64", photoBase64);
	         } catch (IOException e) {
	             e.printStackTrace();
	         }
	     } else {
	         user.setProfilePic(existingUser.getProfilePic());
	     }

	     service.updateUser(user);
	     model.addAttribute("user", user);
	     
	     List<Post> posts = user.getPosts();
	     model.addAttribute("posts", posts);
	     
	     return "myProfile";
	 }
	 
	 
	 
	 
	
	
	
//forgot-password
	
	
	 
	 
	 
	 


	    // Handle forgot password request
//	    @PostMapping("/forgot-password")
//	    public String processForgotPassword(@RequestParam String email, Model model)
//	    {
//	        User user = service.getUserByEmail(email);
//	        if (user != null) {
//	            String token = UUID.randomUUID().toString(); // Generate a unique token
//	            // Save the token in the database or cache with an expiry time
//	            
//	            // Send email with reset link
//	            String resetLink = "http://localhost:8080/reset-password?token=" + token;
//	            sendResetEmail(email, resetLink);
//	            model.addAttribute("msg", "Reset link sent to your email.");
//	        } else {
//	            model.addAttribute("msg", "Email not found.");
//	        }
//	        return "index"; // Redirect to the index page or show a success message
//	    }
//
//	    private void sendResetEmail(String email, String resetLink) {
//	        SimpleMailMessage message = new SimpleMailMessage();
//	        message.setTo(email);
//	        message.setSubject("Password Reset Request");
//	        message.setText("To reset your password, click the link below:\n" + resetLink);
//	        emailSender.send(message);
//	    }
	
	 
	 
	 @GetMapping("/forgot-password")
	    public String showForgotPasswordPage() {
	        return "forgotPassword";
	    }

	    @PostMapping("/forgot-password")
	    public String processForgotPassword(@RequestParam String email, Model model) {
	        User user = service.getUserByEmail(email);
	        System.out.println(user);
	        if (user != null) {
	            String token = UUID.randomUUID().toString(); // Generate a unique token
	            System.out.println("token generate ayindhi "+token);
	            System.out.println("Attempting to create token for email: " + email);
	            service.createPasswordResetTokenForUser (email, token); // Save token
	            	
	            String resetLink = "http://localhost:8080/reset-password?token=" + token;
	            sendResetEmail(email, resetLink); // Call the method
	            model.addAttribute("msg", "Reset link sent to your email.");
	        } else {
	            model.addAttribute("msg", "Email not found.");
	        }
	        return "index"; // Redirect or show a success message
	    }

	    private void sendResetEmail(String email, String resetLink) {
	        SimpleMailMessage message = new SimpleMailMessage();
	        message.setTo(email);
	        message.setSubject("Password Reset Request");
	        message.setText("To reset your password, click the link below:\n" + resetLink);
	        emailSender.send(message);
	    }
	 

	 

	    @PostMapping("/reset-password")
	    public String resetPassword(@RequestParam String token, @RequestParam String newPassword, @RequestParam String confirmPassword, Model model) {
	        PasswordResetToken passwordResetToken = service.getToken(token);
	        System.out.println("Token received: " + token);
	        // Check if token is valid and not expired
	        if (passwordResetToken != null && passwordResetToken.getExpiryDate().isAfter(LocalDateTime.now())) {
	            if (newPassword.equals(confirmPassword)) {
	                User user = service.getUserByEmail(passwordResetToken.getEmail());
	                user.setPassword(newPassword); // Make sure to hash the password before saving
	                System.out.println("new passworduu "+newPassword);
	                service.updateUser (user);
	                model.addAttribute("msg", "Password has been reset successfully.");
	                return "index"; // Redirect to login page
	            } else {
	                model.addAttribute("msg", "Passwords do not match.");
	            }
	        } else {
	            model.addAttribute("msg", "Invalid or expired token.");
	        }
	        return "resetPassword"; // Show the reset password page again
	    }
	    
	    


	 
	 
	 
	 
	 
	 
	
//	@PostMapping("/updateProfile")
//	public String updateProfile(@RequestParam(defaultValue = "") String gender, 
//	                            @RequestParam String dob,
//	                            @RequestParam String city,
//	                            @RequestParam String bio,
//	                            @RequestParam String college,
//	                            @RequestParam String linkedIn,
//	                            @RequestParam String gitHub,
//	                            @RequestParam MultipartFile profilePic,
//	                            HttpSession session,
//	                            Model model) {
//	    String username = (String) session.getAttribute("username");
//	    User user = service.getUserByUsername(username);
//
//	    // Retrieve existing user data from database
//	    User existingUser = service.getUserByUsername(username);
//
//	    // Update only the fields that have been changed
//	    if (gender!=null&&!gender.isEmpty()) {
//	        user.setGender(gender);
//	    } else {
//	        user.setGender(existingUser.getGender());
//	    }
//
//	    if (!dob.isEmpty()) {
//	        user.setDob(dob);
//	    } else {
//	        user.setDob(existingUser.getDob());
//	    }
//
//	    if (!city.isEmpty()) {
//	        user.setCity(city);
//	    } else {
//	        user.setCity(existingUser.getCity());
//	    }
//
//	    if (!bio.isEmpty()) {
//	        user.setBio(bio);
//	    } else {
//	        user.setBio(existingUser.getBio());
//	    }
//
//	    if (!college.isEmpty()) {
//	        user.setCollege(college);
//	    } else {
//	        user.setCollege(existingUser.getCollege());
//	    }
//
//	    if (!linkedIn.isEmpty()) {
//	        user.setLinkedIn(linkedIn);
//	    } else {
//	        user.setLinkedIn(existingUser.getLinkedIn());
//	    }
//
//	    if (!gitHub.isEmpty()) {
//	        user.setGitHub(gitHub);
//	    } else {
//	        user.setGitHub(existingUser.getGitHub());
//	    }
//	    System.out.println("Profile Pic: " + profilePic);
//	    if (profilePic != null && !profilePic.isEmpty()) {
//	        try {
//	            user.setProfilePic(profilePic.getBytes());
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	    } else {
//	        user.setProfilePic(existingUser.getProfilePic());
//	    }
//
//	    service.updateUser(user);
//	    model.addAttribute("user", user);
//	    
//	    
//	    
//	   
//		   List<Post> posts = user.getPosts();
//		   model.addAttribute("posts", posts);
//	    
//	    return "myProfile";
//	}
	    
	    
	    


}
