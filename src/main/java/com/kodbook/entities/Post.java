package com.kodbook.entities;


import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import jakarta.persistence.Basic;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Post 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String caption;
	private int likes;
//	private List<String> comments;
	
	
//	private List<String> commentTime;
	
	@ManyToOne
	
	private User user;
	
	@OneToMany
//	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> commenters;
	
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] photo;
	
	
	public String getPhotoBase64()
	{
		if(photo==null)
		{
			return null;
		}
		return Base64.getEncoder().encodeToString(photo);
	}
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "LONGBLOB")
	private byte[] video;

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getVideoBase64()
	{
	    if(video==null)
	    {
	        return null;
	    }
	    return Base64.getEncoder().encodeToString(video);
	}


	


	public Post(Long id, String caption, int likes, User user, List<Comment> commenters, byte[] photo, byte[] video) {
		super();
		this.id = id;
		this.caption = caption;
		this.likes = likes;
		this.user = user;
		this.commenters = commenters;
		this.photo = photo;
		this.video = video;
	}
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCaption() {
		return caption;
	}
	public void setCaption(String caption) {
		this.caption = caption;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<Comment> getCommenters() {
		return commenters;
	}
	public void setCommenters(List<Comment> commenters) {
		this.commenters = commenters;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public byte[] getVideo() {
		return video;
	}
	public void setVideo(byte[] video) {
		this.video = video;
	}

	
	
	
	
	@Override
	public String toString() {
	    return "Post [id=" + id + ", caption=" + caption + ", likes=" + likes + ", commenters=" + commenters +  ", user="
	            + (user != null ? "User[id=" + user.getId() + "]" : "null") + ", photo=" + (photo != null ? Arrays.toString(photo) : "null")+ ", video=" + Arrays.toString(video) + "]";
	}


	
	
	
}
