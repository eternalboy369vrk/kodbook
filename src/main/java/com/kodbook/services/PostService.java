package com.kodbook.services;

import java.util.List;


import com.kodbook.entities.Post;
import com.kodbook.entities.User;

public interface PostService {

	void createPost(Post post);

	List<Post> fetchAllPosts();

	Post getPost(Long id);

	void updatePost(Post post);
	
//	void addComment(Comment comment);

	void likePost(Long id, User user);

	void addComment(Long id, String comment, User user);

	void updateePost(Post post);
	
	

}
