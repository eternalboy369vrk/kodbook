package com.kodbook.services;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodbook.entities.Comment;
import com.kodbook.entities.Notification;
import com.kodbook.entities.Post;
import com.kodbook.entities.PostLike;
import com.kodbook.entities.User;
import com.kodbook.repositories.CommentRepository;
import com.kodbook.repositories.PostLikeRepository;
import com.kodbook.repositories.PostRepository;

@Service
public class PostServiceImplementation implements PostService{
	@Autowired
	PostRepository repo;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Autowired
    private PostLikeRepository postLikeRepository;
	
	@Autowired
    private NotificationService notService;
	
	@Autowired
    private CommentService commentService;

	@Override
	public void createPost(Post post) {
		repo.save(post);
	}

//	@Override
//	public  List<Post> fetchAllPosts() {
//		return repo.findAll();
//	}
	
	@Override
	public List<Post> fetchAllPosts() {
	    List<Post> posts = repo.findAll();
	    for (Post post : posts) {
	        if (post.getCommenters() != null) {
	            post.setCommenters(commentRepository.findByPost(post));
	        }
	    }
	    return posts;
	}

	@Override
	public Post getPost(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).get();
	}

	@Override
	public void updatePost(Post post) {
		repo.save(post);
		
	}
	
	public void updateePost(Post post) {
	    if (post.getId() != null && repo.existsById(post.getId())) {
	    	System.out.println(post.getId());
	    	repo.save(post); // This will update the existing post
	    } else {
	        throw new IllegalArgumentException("Post ID is invalid or does not exist.");
	    }
	}

//	@Override
//	public void addComment(Comment comment) {
//	    commentRepository.save(comment);
//	    Post post = comment.getPost();
//	    if (post.getCommenters() == null) {
//	        post.setCommenters(new ArrayList<>());
//	    }
//	    post.getCommenters().add(comment);
//	    repo.save(post);
//	}
	
//	for add commnet and also notification
	
//	@Override
//	public void addComment(Long id, String comment, User user) {
//	    Post post = repo.findById(id).get();
//	    
//	    Comment newComment = new Comment();
//	    newComment.setComment(comment);
//	    newComment.setPost(post);
//	    newComment.setCommenterUsername(user.getUsername());
//	    newComment.setCommentTime(new Date());
//	    
//	    post.getCommenters().add(newComment);
//	    repo.save(post);
//	    
//	    Notification notification = new Notification();
//	    notification.setMessage("commented on your post");
//	    notification.setDate(new Date());
//	    notification.setUser(post.getUser());
//	    notification.setPost(post);
//	    notService.saveNotification(notification);
//	}
	
	
	//new way
	
	
//	@Override
//	public void addComment(Long id, String comment, User user) {
//	    Post post = repo.findById(id).get();
//	    
//	    Comment newComment = new Comment();
//	    newComment.setComment(comment);
//	    newComment.setPost(post);
//	    newComment.setCommenterUsername(user.getUsername());
//	    newComment.setCommentTime(new Date());
//	    
//	    post.getCommenters().add(newComment);
//	    repo.save(post);
//	    
//	    // Save notification for the user who owns the post
//	    if (!user.getUsername().equals(post.getUser().getUsername())) {
//	        Notification notification = new Notification();
//	        notification.setMessage(user.getUsername() + " commented on your post");
//	        notification.setDate(new Date());
//	        notification.setUser(post.getUser());
//	        notification.setPost(post);
//	        notService.saveNotification(notification);
//	    }
//	}
//	
	
//	for like the post only one time
	
//	 @Override
//	    public void likePost(Long id, User user) {
//	        Post post = repo.findById(id).get();
//	        PostLike postLike = postLikeRepository.findByPostAndUser(post, user);
//	        
//	        if (postLike == null) {
//	            post.setLikes(post.getLikes() + 1);
//	            repo.save(post);
//	            
//	            PostLike newPostLike = new PostLike();
//	            newPostLike.setPost(post);
//	            newPostLike.setUser(user);
//	            postLikeRepository.save(newPostLike);
//	        }
//	    }
//	 
	 
//	 @Override
//	 public void likePost(Long id, User user) {
//	     Post post = repo.findById(id).get();
//	     PostLike postLike = postLikeRepository.findByPostAndUser(post, user);
//	     System.out.println("Post ki like"+postLike);
//	     if (postLike == null) {
//	         post.setLikes(post.getLikes() + 1);
//	         repo.save(post);
//	         
//	         PostLike newPostLike = new PostLike();
//	         newPostLike.setPost(post);
//	         newPostLike.setUser(user);
//	         postLikeRepository.save(newPostLike);
//	     } else {
//	         post.setLikes(post.getLikes() - 1);
//	         repo.save(post);
//	         postLikeRepository.delete(postLike);
//	     }
//	 }
	 
	 
	 
//	 @Override
//	 public void likePost(Long id, User user) {
//	     Post post = repo.findById(id).get();
//	     PostLike postLike = postLikeRepository.findByPostAndUser(post, user);
//	     
//	     if (postLike == null) {
//	         post.setLikes(post.getLikes() + 1);
//	         repo.save(post);
//	         
//	         PostLike newPostLike = new PostLike();
//	         newPostLike.setPost(post);
//	         newPostLike.setUser(user);
//	         postLikeRepository.save(newPostLike);
//	         
//	         Notification notification = new Notification();
//	         notification.setMessage("liked your post");
//	         notification.setDate(new Date());
//	         notification.setUser(post.getUser());
//	         notification.setPost(post);
//	         notService.saveNotification(notification);
//	     } else {
//	         post.setLikes(post.getLikes() - 1);
//	         repo.save(post);
//	         postLikeRepository.delete(postLike);
//	         
//	         Notification notification = new Notification();
//	         notification.setMessage("unliked your post");
//	         notification.setDate(new Date());
//	         notification.setUser(post.getUser());
//	         notification.setPost(post);
//	         notService.saveNotification(notification);
//	     }
//	 }
	
	
	
	
	//new way
	
	@Override
	public void likePost(Long id, User user) {
	    Post post = repo.findById(id).get();
	    PostLike postLike = postLikeRepository.findByPostAndUser(post, user);
	    
	    if (postLike == null) {
	        post.setLikes(post.getLikes() + 1);
	        repo.save(post);
	        
	        PostLike newPostLike = new PostLike();
	        newPostLike.setPost(post);
	        newPostLike.setUser(user);
	        postLikeRepository.save(newPostLike);
	        
	        // Save notification for the user who owns the post
	        if (!user.getUsername().equals(post.getUser().getUsername())) {
	            Notification notification = new Notification();
	            notification.setMessage(user.getUsername() + " liked your post: " + post.getCaption());
	            notification.setDate(new Date());
	            notification.setUser(post.getUser());
	            notification.setPost(post);
	            notService.saveNotification(notification);
	        }
	    } else {
	        post.setLikes(post.getLikes() - 1);
	        repo.save(post);
	        postLikeRepository.delete(postLike);
	        
	        // Save notification for the user who owns the post
	        if (!user.getUsername().equals(post.getUser().getUsername())) {
	            Notification notification = new Notification();
	            notification.setMessage(user.getUsername() + " unliked your post: " + post.getCaption());
	            notification.setDate(new Date());
	            notification.setUser(post.getUser());
	            notification.setPost(post);
	            notService.saveNotification(notification);
	        }
	    }
	}

	
	
	@Override
	public void addComment(Long id, String comment, User user) {
	    try {
	        System.out.println("Adding comment to post with id: " + id);
	        Post post = repo.findById(id).get();
	        

	        if (user == null) {
	            throw new Exception("User not found");
	        }

	        Comment newComment = new Comment();
	        newComment.setComment(comment);
	        newComment.setPost(post);
	        newComment.setCommenterUsername(user.getUsername());
	        newComment.setCommentTime(new Date());

	        // Save the comment
	        commentService.saveComment(newComment);

	        // Add the comment to the post
	        post.getCommenters().add(newComment);
	        repo.save(post);

	        // Save notification for the user who owns the post
	        if (!user.getUsername().equals(post.getUser().getUsername())) {
	            Notification notification = new Notification();
	            notification.setMessage(user.getUsername() + " commented on your post: " + comment);
	            notification.setDate(new Date());
	            notification.setUser(post.getUser());
	            notification.setPost(post);
	            notService.saveNotification(notification);
	        }
	    } catch (Exception e) {
	        System.out.println("Error adding comment: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	
	
	
	
	
	
	
//	@Override
//	public void likePost(Long id, User user) {
//	    Post post = repo.findById(id).get();
//	    PostLike postLike = postLikeRepository.findByPostAndUser(post, user);
//	    
//	    if (postLike == null) {
//	        post.setLikes(post.getLikes() + 1);
//	        repo.save(post);
//	        
//	        PostLike newPostLike = new PostLike();
//	        newPostLike.setPost(post);
//	        newPostLike.setUser(user);
//	        postLikeRepository.save(newPostLike);
//	        
//	        // Save notification for the user who owns the post
//	        if (!user.getUsername().equals(post.getUser().getUsername())) {
//	            Notification notification = new Notification();
//	            notification.setMessage(user.getUsername() + " liked your post: " + post.getCaption());
//	            notification.setDate(new Date());
//	            notification.setUser(post.getUser());
//	            notification.setPost(post);
//	            notService.saveNotification(notification);
//	        }
//	    } else {
//	        post.setLikes(post.getLikes() - 1);
//	        repo.save(post);
//	        postLikeRepository.delete(postLike);
//	        
//	        // Save notification for the user who owns the post
//	        if (!user.getUsername().equals(post.getUser().getUsername())) {
//	            Notification notification = new Notification();
//	            notification.setMessage(user.getUsername() + " unliked your post: " + post.getCaption());
//	            notification.setDate(new Date());
//	            notification.setUser(post.getUser());
//	            notification.setPost(post);
//	            notService.saveNotification(notification);
//	        }
//	    }
//	}



	 
}
