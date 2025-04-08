package com.kodbook.entities;


import java.util.Base64;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    @ManyToOne
//    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
//    @JoinColumn(name = "user_id")
    private User user;
    
    private String commenterUsername;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "LONGBLOB")
    private byte[] commenterProfilePic;

    private Date commentTime;
    
    
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Comment(Long id, String comment, Post post, User user, String commenterUsername, byte[] commenterProfilePic,
			Date commentTime) {
		super();
		this.id = id;
		this.comment = comment;
		this.post = post;
		this.user = user;
		this.commenterUsername = commenterUsername;
		this.commenterProfilePic = commenterProfilePic;
		this.commentTime = commentTime;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
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


	public String getCommenterUsername() {
		return commenterUsername;
	}


	public void setCommenterUsername(String commenterUsername) {
		this.commenterUsername = commenterUsername;
	}


	public byte[] getCommenterProfilePic() {
	    return commenterProfilePic;
	}

	public String getCommenterProfilePicBase64() {
	    if (commenterProfilePic == null) {
	        return null;
	    }
	    return Base64.getEncoder().encodeToString(commenterProfilePic);
	}


	public void setCommenterProfilePic(byte[] commenterProfilePic) {
		this.commenterProfilePic = commenterProfilePic;
	}


	public Date getCommentTime() {
		return commentTime;
	}


	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}


//	@Override
//	public String toString() {
//	    String commenterProfilePicBase64 = 
//	    		commenterProfilePic != null ?
//	    				Base64.getEncoder().encodeToString(commenterProfilePic) : "null";
//	    return "Comment [id=" + id + ", comment=" + comment 
//	    		+ ", post=" + post.getId()
//	            + ", user=" + user + ", commenterUsername=" 
//	    		+ commenterUsername + ", commenterProfilePic=" 
//	            + commenterProfilePicBase64
//	            + ", commentTime=" + commentTime + "]";
//	}
	
	@Override
	public String toString() {
	    String commenterProfilePicBase64 = 
	    		commenterProfilePic !=
	    		null ? Base64.getEncoder().encodeToString(commenterProfilePic) : "null";
	    return "Comment [id=" + id + ", comment=" + comment + ","
	    		+ " post=" + (post != null ? post.getId() : "null")
	            + ", user=" + user + ", commenterUsername=" + 
	    		commenterUsername + ", commenterProfilePic=" + commenterProfilePicBase64
	            + ", commentTime=" + commentTime + "]";
	}


	



	

	

	

	
	

    
}
