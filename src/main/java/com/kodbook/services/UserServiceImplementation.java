package com.kodbook.services;




import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodbook.entities.PasswordResetToken;
import com.kodbook.entities.User;
import com.kodbook.repositories.PasswordResetTokenRepository;
import com.kodbook.repositories.UserRepository;

@Service
public class UserServiceImplementation implements UserService{
	@Autowired
	UserRepository repo;
	
	 @Autowired
	    private PasswordResetTokenRepository tokenRepository;
	
	@Override
	public void addUser(User user) {
		System.out.println(user);
		repo.save(user); 
		
	}

	@Override
	public boolean userExists(String username, String email) {
		User user1=repo.findByUsername(username);
		User user2=repo.findByEmail(email);
		
		if(user1!=null||user2!=null)
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean usernameExists(String username) {
		return repo.findByUsername(username) != null;
	}

	@Override
	public boolean emailExists(String email) {
		return repo.findByEmail(email) != null;
	}

//	@Override
//	public boolean validateUser(String username, String password) {
//		String dbpass=repo.findByUsername(username).getPassword();
//		if(password.equals(dbpass))
//		{
//			return true;
//		}
//		return false;
//	}
	
	
//	@Override
//	public boolean validateUser(String username, String password) {
//	    User user = repo.findByUsername(username);
//	    System.out.println(user);
//	    if (user == null) {
//	        return false; 
//	    }
//	    String dbpass = user.getPassword();
//	  
//	    if (password.equals(dbpass)) {
//	        return true;
//	    }
//	    return false;
//	}
	
	
	//new way
	
	@Override
	public boolean validateUser(String credential, String password) {
	    User user = repo.findByUsername(credential);
	    if (user == null) {
	        user = repo.findByEmail(credential);
	    }
	    if (user == null) {
	        return false;
	    }
	    String dbpass = user.getPassword();
	    if (password.equals(dbpass)) {
	        return true;
	    }
	    return false;
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return repo.findByUsername(username);
	}

	@Override
	public void updateUser(User user) {
		repo.save(user);
		
	}

	

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return repo.findByUsername(username);
	}

	@Override
	public void saveUser(User user) {
		repo.save(user);
		
	}
	
	@Override
	public User getUserByUser(User user) {
	    return getUserByUsername(user.getUsername());
	}


	
	
	
	
	

//	    @Override
//	    public void createPasswordResetTokenForUser (String email, String token) {
//	        PasswordResetToken passwordResetToken = new PasswordResetToken();
//	        passwordResetToken.setEmail(email);
//	        passwordResetToken.setToken(token);
//	        passwordResetToken.setExpiryDate(LocalDateTime.now().plusHours(1)); // Token valid for 1 hour
//	        tokenRepository.save(passwordResetToken);
//	    }
	 
	 @Override
	 public void createPasswordResetTokenForUser (String email, String token) {
	     PasswordResetToken passwordResetToken = new PasswordResetToken();
	     passwordResetToken.setEmail(email);
	     passwordResetToken.setToken(token);
	     passwordResetToken.setExpiryDate(LocalDateTime.now().plusHours(1)); // Token valid for 1 hour
	     tokenRepository.save(passwordResetToken);
	     System.out.println("Token created: " + token);
	 }

//	    @Override
//	    public PasswordResetToken getToken(String token) {
//	    	System.out.println("tokenuuu "+token);
//	        return tokenRepository.findByToken(token);
//	    }
	 
	 @Override
	 public PasswordResetToken getToken(String token) {
	     if (token == null || token.isEmpty()) {
	         System.out.println("Token is null or empty");
	         return null;
	     }
	     System.out.println("Fetching token: " + token);
	     PasswordResetToken retrievedToken = tokenRepository.findByToken(token);
	     if (retrievedToken == null) {
	         System.out.println("No token found for: " + token);
	     } else {
	         System.out.println("Token found: " + retrievedToken);
	     }
	     return retrievedToken;
	 }


	    @Override
		public User getUserByEmail(String email) {
		    return repo.findByEmail(email);
		}



	

	
}
