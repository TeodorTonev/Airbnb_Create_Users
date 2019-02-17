package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserStorage {
	public static final int MIN_LENGTH_EMAIL = 5;
	// Create user -> has the user entered (true or false)
	private Map<User, Boolean> createAndLogIn;

	private static UserStorage instance;

	private UserStorage() {
		this.createAndLogIn = new HashMap<>();
	}

	public static UserStorage getInstance() {
		if (instance == null) {
			instance = new UserStorage();
		}
		return instance;
	}

	public void register(String emailAddress, String firstName, String lastName, String password) throws UserException {
    	User user = new User(emailAddress, firstName, lastName,password) ;
    	for(User u : createAndLogIn.keySet()) {
    		if (u.equals(user)){
    			throw new UserException("User already exists");
    		}
    	}
    	createAndLogIn.put(user, false);
    	
    }

	public User logIn(String emailAddress, String password) throws UserException {
		User user = new User(emailAddress, password);
		for (User u : createAndLogIn.keySet()) {
			if (u.equals(user)) {
				user = u;
				break;
			}
		}
		if (this.createAndLogIn.containsKey(user) && isValidEmail(emailAddress) && isValidPassword(password)) {
			this.createAndLogIn.put(user, true);
		} else {
			throw new UserException("Invalid email address or password");
		}
		return user;
	}

	public void logOut(User user) throws UserException {
		if (user != null && this.createAndLogIn.get(user).booleanValue() == true && this.createAndLogIn.containsKey(user)) {
			this.createAndLogIn.put(user, false);
		} else {
			throw new UserException("Wrong user");
		}
	}

	public void listAllLogInUsers() {
		for (Map.Entry<User, Boolean> entry : this.createAndLogIn.entrySet()) {
			User user = entry.getKey();
			if (entry.getValue()) {
				System.out.println("The list is: " + user);
			}
		}
	}

	public void sendInvitesTravelToYourFriend(String emailAddress) {
		// send email to emailAddress ...
	}

	public void setCreatedUser(User user) {
		this.createAndLogIn.put(user, true);
	}

	public void removeCreatedUser(User user) {
		this.createAndLogIn.remove(user);
	}

	private boolean isValidPassword(String password) {
		if (password != null && password.trim().length() > 0)
			return true;
		return false;
	}

	private boolean isValidEmail(String emailAddress) {
		if (emailAddress != null && emailAddress.contains("@") && emailAddress.trim().length() > MIN_LENGTH_EMAIL)
			return true;
		return false;
	}
	public boolean isLoggedIn(User user) {
		if (user != null) {
			return this.createAndLogIn.get(user);
		}
		return false;
	}

}
