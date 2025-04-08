package com.kodbook.services;



import com.kodbook.entities.PasswordResetToken;
import com.kodbook.entities.User;


public interface UserService {

	void addUser(User user);
	
	boolean userExists(String username, String email);

	
	//for checking username and email separately for informing to user if the usernaqme or mail alrady exist
	boolean usernameExists(String username);

	boolean emailExists(String email);

	boolean validateUser(String username, String password);

	User getUserByUsername(String username);

	void updateUser(User user);

	User getUser(String username);

	void saveUser(User user);

	User getUserByUser(User user);



	User getUserByEmail(String email);
	
	void createPasswordResetTokenForUser (String email, String token);
       PasswordResetToken getToken(String token);
//
//	void saveToken(PasswordResetToken passwordResetToken);





	
	
	

	

	

}
