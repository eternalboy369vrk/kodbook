package com.kodbook.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.ManyToOne;

@Entity
public class Notification {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String message;
    
    private Date date;
    
    @ManyToOne
//    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
//    @JoinColumn(name = "post_id")
    private Post post;

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Notification(Long id, String message, Date date, User user, Post post) {
		super();
		this.id = id;
		this.message = message;
		this.date = date;
		this.user = user;
		this.post = post;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", message=" + message + ", date=" + date + ", user=" + user + ", post="
				+ post + "]";
	}
    
    
}