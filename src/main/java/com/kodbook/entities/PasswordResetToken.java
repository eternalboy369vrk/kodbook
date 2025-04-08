package com.kodbook.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


import java.time.LocalDateTime;

@Entity
public class PasswordResetToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    
    private String email;

    private LocalDateTime expiryDate;
    

    
    
    public PasswordResetToken() {
		super();
		// TODO Auto-generated constructor stub
	}


	public PasswordResetToken(Long id, String token, String email, LocalDateTime expiryDate) {
		super();
		this.id = id;
		this.token = token;
		this.email = email;
		this.expiryDate = expiryDate;
		
	}






	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public LocalDateTime getExpiryDate() {
		return expiryDate;
	}


	public void setExpiryDate(LocalDateTime expiryDate) {
		this.expiryDate = expiryDate;
	}


	


	@Override
	public String toString() {
		return "PasswordResetToken [id=" + id + ", token=" + token + ", email=" + email + ", expiryDate=" + expiryDate
				+  "]";
	}

	

	

    // Getters and Setters
    
    
    
    
    
}