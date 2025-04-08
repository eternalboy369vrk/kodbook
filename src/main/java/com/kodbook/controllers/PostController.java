package com.kodbook.controllers;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kodbook.entities.Comment;
import com.kodbook.entities.Post;
import com.kodbook.entities.User;
import com.kodbook.services.CommentService;
import com.kodbook.services.PostService;
import com.kodbook.services.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class PostController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	PostService service;
	

//for creating the post 	
	
	
//	@PostMapping("/createPost")
//	public String createPost(@RequestParam ("caption") String caption,
//	        @RequestParam("photo") MultipartFile photo,
//	        Model model, HttpSession session, RedirectAttributes redirectAttributes) {
//	    // Process the post creation
//	    String username = (String) session.getAttribute("username");
//	    User user = userService.getUser(username);
//	    Post post = new Post();
//	    post.setUser(user);
//	    post.setCaption(caption);
//	    
//		    try {						
//		        post.setPhoto(photo.getBytes());
//		    } catch (IOException e) {
//		        // TODO Auto-generated catch block
//		        e.printStackTrace();
//		    }
//	    service.createPost(post);
//	    
//	    
//	  //updating user object
//		List<Post> posts = user.getPosts();
//		if(posts == null) {
//			posts = new ArrayList<Post>();
//		}
//		posts.add(post);
//		user.setPosts(posts);
//		userService.updateUser(user);
//		
//List<Post> allPosts = service.fetchAllPosts();
//model.addAttribute("allPosts", allPosts);
//	    // Redirect to the home page
//	    redirectAttributes.addFlashAttribute("message", "Post created successfully!");
//	    return "redirect:/goHome";
//	}
	
//	@PostMapping("/updatePost")
//	public String updatePost(@RequestParam Long id, 
//	                         @RequestParam(required = false) String caption,
//	                         @RequestParam(required = false) MultipartFile photo,
//	                         HttpSession session, 
//	                         RedirectAttributes redirectAttributes) {
//		System.out.println("Received ID: " + id);
//	    // Retrieve the user's ID from the session
//	    String username = (String) session.getAttribute("username");
//	    User user = userService.getUser (username);
//	    
//	    // Check if the user is logged in
//	    if (user == null) {
//	        redirectAttributes.addFlashAttribute("error", "You need to log in first.");
//	        return "redirect:/login"; // Redirect to login page if not logged in
//	    }
//
//	    // Retrieve the post to update
//	    Post post = service.getPost(id);
//	    if (post == null) {
//	        redirectAttributes.addFlashAttribute("error", "Post not found.");
//	        return "redirect:/goHome"; // Redirect if the post does not exist
//	    }
//
//	    // Update the post's caption if provided
//	    if (caption != null && !caption.trim().isEmpty()) {
//	        post.setCaption(caption);
//	    }
//
//	    // Update the post's photo if a new one is provided
//	    if (photo != null && !photo.isEmpty()) {
//	        try {
//	            post.setPhoto(photo.getBytes());
//	        } catch (IOException e) {
//	            redirectAttributes.addFlashAttribute("error", "Failed to upload photo.");
//	            return "redirect:/goHome"; // Redirect with error if upload fails
//	        }
//	    }
//
//	    // Save the updated post
//	    service.updatePost(post);
//
//	    // Add a success message
//	    redirectAttributes.addFlashAttribute("message", "Post updated successfully!");
//	    return "redirect:/goHome"; // Redirect to the home page
//	}
	
	
	
//	
//	@PostMapping("/editPost")
//	public String editPost(@RequestParam Long id,
//	                       @RequestParam(required = false) String caption,
//	                       @RequestParam(required = false) MultipartFile photo,
//	                       HttpSession session,
//	                       RedirectAttributes redirectAttributes) {
//	    // Retrieve the user's ID from the session
//	    String username = (String) session.getAttribute("username");
//	    User user = userService.getUserByUsername(username);
//
//	    // Check if the user is logged in
//	    if (user == null) {
//	        redirectAttributes.addFlashAttribute("error", "You need to log in first.");
//	        return "redirect:/login"; // Redirect to login page if not logged in
//	    }
//
//	    // Retrieve the post to update
//	    Post post = service.getPost(id);
//	    if (post == null) {
//	        redirectAttributes.addFlashAttribute("error", "Post not found.");
//	        return "redirect:/goHome"; // Redirect if the post does not exist
//	    }
//
//	    // Update the post's caption if provided
//	    if (caption != null && !caption.trim().isEmpty()) {
//	        post.setCaption(caption);
//	    }
//
//	    // Update the post's photo if a new one is provided
//	    if (photo != null && !photo.isEmpty()) {
//	        try {
//	            post.setPhoto(photo.getBytes());
//	        } catch (IOException e) {
//	            redirectAttributes.addFlashAttribute("error", "Failed to upload photo.");
//	            return "redirect:/goHome"; // Redirect with error if upload fails
//	        }
//	    }
//
//	    // Save the updated post
//	    service.updatePost(post);
//
//	    // Add a success message
//	    redirectAttributes.addFlashAttribute("message", "Post updated successfully!");
//	    return "redirect:/goHome"; // Redirect to the home page
//	}
	
	
	
//	@GetMapping("/editPostForm/{id}")
//	public String showEditPostForm(@PathVariable Long id, Model model, HttpSession session) {
//	    // Fetch the post by id
//	    Post post = service.getPost(id);
//	    model.addAttribute("post", post);
//	    return "editPost"; // Return the view name
//	}

	
	
	@PostMapping("/editPost")
	public String editPost(@RequestParam Long id,
	                       @RequestParam(required = false) String caption,
	                       @RequestParam(required = false) MultipartFile photo,
	                       HttpSession session,
	                       RedirectAttributes redirectAttributes,Model model) {
	    // Retrieve the user's ID from the session
		System.out.println("Edit Post ID: " + id); 
	    String username = (String) session.getAttribute("username");
	    User user = userService.getUserByUsername(username);

	    // Check if the user is logged in
	    if (user == null) {
	    	model.addAttribute("error", "You need to log in first.");
	        redirectAttributes.addFlashAttribute("error", "You need to log in first.");
	        return "redirect:/login"; // Redirect to login page if not logged in
	    }

	    // Retrieve the post to update
	    Post post = service.getPost(id);
	    if (post == null) {
	    	model.addAttribute("error", "Post not found.");
	        redirectAttributes.addFlashAttribute("error", "Post not found.");
	        return "redirect:/goHome"; // Redirect if the post does not exist
	    }

	    // Security check: Ensure only the post owner can edit
	    if (!post.getUser ().equals(user)) {
	    	model.addAttribute("error", "You are not authorized to edit this post.");
	        redirectAttributes.addFlashAttribute("error", "You are not authorized to edit this post.");
	        return "redirect:/goHome"; // Redirect if the user is not authorized
	    }

	    // Update the post's caption if provided
	    if (caption != null && !caption.trim().isEmpty()) {
	        post.setCaption(caption);
	    }

	    // Validate and update the post's photo if a new one is provided
	    if (photo != null && !photo.isEmpty()) {
	        // Validate file type and size (e.g., max size 5MB)
	        String contentType = photo.getContentType();
	        if (!contentType.startsWith("image/")) {
	        	model.addAttribute("error", "Invalid file type. Please upload an image.");
	            redirectAttributes.addFlashAttribute("error", "Invalid file type. Please upload an image.");
	            return "redirect:/goHome"; // Redirect with error if invalid file type
	        }
	        if (photo.getSize() > 5 * 1024 * 1024) { // 5MB limit
	        	model.addAttribute("error", "File size exceeds the maximum limit of 5MB.");
	            redirectAttributes.addFlashAttribute("error", "File size exceeds the maximum limit of 5MB.");
	            return "redirect:/goHome"; // Redirect with error if file size exceeds limit
	        }
	        try {
	            post.setPhoto(photo.getBytes());
	        } catch (IOException e) {
	        	model.addAttribute("error", "Failed to upload photo.");
	            redirectAttributes.addFlashAttribute("error", "Failed to upload photo.");
	            return "redirect:/goHome"; // Redirect with error if upload fails
	        }
	    }

	    // Save the updated post
	    service.updateePost(post);

	    // Add a success message
	    model.addAttribute("message", "Post updated successfully!");
	    redirectAttributes.addFlashAttribute("message", "Post updated successfully!");
	    return "redirect:/goHome"; // Redirect to the home page
	}
	
	@PostMapping("/createPost")
	public String createPost(@RequestParam ("caption") String caption,
	        @RequestParam("photo") MultipartFile photo,@RequestParam("video") MultipartFile video,
	        Model model, HttpSession session, RedirectAttributes redirectAttributes) {
	    // Process the post creation
	    String username = (String) session.getAttribute("username");
	    User user = userService.getUser(username);
	    Post post = new Post();
	    post.setUser(user);
	    post.setCaption(caption);
	    if (photo != null && !photo.isEmpty()) {
		    try {						
		        post.setPhoto(photo.getBytes());
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		    }
	    }
	    if (video != null && !video.isEmpty()) {
	        try {
	            post.setVideo(video.getBytes());
	        } catch (IOException e) {
	            // Handle the exception
	        }
	    }
	    service.createPost(post);
	    
	    
	  //updating user object
		List<Post> posts = user.getPosts();
		if(posts == null) {
			posts = new ArrayList<Post>();
		}
		posts.add(post);
		user.setPosts(posts);
		userService.updateUser(user);
		
List<Post> allPosts = service.fetchAllPosts();
model.addAttribute("allPosts", allPosts);
	    // Redirect to the home page
	    redirectAttributes.addFlashAttribute("message", "Post created successfully!");
	    return "redirect:/goHome";
	}
	
	
	
	
	

	 @GetMapping("/likePost")
	 public String likePost(@RequestParam Long id, HttpSession session, RedirectAttributes redirectAttributes) {
	     String username = (String) session.getAttribute("username");
	     User user = userService.getUserByUsername(username);
	     service.likePost(id, user);
	     redirectAttributes.addFlashAttribute("message", "Post liked successfully!");
	     return "redirect:/goHome";
	 }
	

	

	
	
	@PostMapping("/addComment")
	public String addComment(@RequestParam Long id, @RequestParam String comments,
	        RedirectAttributes redirectAttributes, Model model, HttpSession session) {
	    Post post = service.getPost(id);
System.out.println("addc omment ---------"+post);
	    // Create a new Comment object
	    Comment comment = new Comment();
	    System.out.println("inside commentadd method-----"+comment);
	    comment.setComment(comments);
	    comment.setPost(post);
	    comment.setCommenterUsername((String) session.getAttribute("username"));

	    // Convert the base64-encoded profile picture to a byte array
	    String commenterProfilePicBase64 = (String) session.getAttribute("photoBase64");
	    comment.setCommenterProfilePic(Base64.getDecoder().decode(commenterProfilePicBase64));

	    comment.setCommentTime(new Date());

	    // Save the Comment object
	    commentService.saveComment(comment);

	    // Add the Comment object to the comments field in the Post entity
	    post.getCommenters().add(comment);
	    
	   
	    service.updatePost(post);
	    User user = (User) session.getAttribute("user");
	    if (user == null) {
	        user = userService.getUserByUsername((String) session.getAttribute("username"));
	    }
	    System.out.println("User---inside add commnet method ---"+user);
	    service.addComment(id, comments, user);
	    List<Post> allPosts = service.fetchAllPosts();
	    model.addAttribute("allPosts", allPosts);
	    model.addAttribute("message", "Comment added successfully!");
	    return "redirect:/goHome";
	}
	

	
	
	@GetMapping("/home")
	public String home(Model model) {
	    List<Post> allPosts = service.fetchAllPosts();
	    if (allPosts == null) {
	        System.out.println("allPosts is null");
	    } else {
	        model.addAttribute("allPosts", allPosts);
	    }
	    return "home";
	}
	

}
