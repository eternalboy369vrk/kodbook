
package com.kodbook.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;

@Entity
public class PostLike {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
//    @JoinColumn(name = "post_id")
    private Post post;
    
    @ManyToOne
//    @JoinColumn(name = "user_id")
    private User user;

	public PostLike() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostLike(Long id, Post post, User user) {
		super();
		this.id = id;
		this.post = post;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "PostLike [id=" + id + ", post=" + post + ", user=" + user + "]";
	}
    
    
}
