package com.company;

import java.util.HashSet;
import java.util.Set;

public abstract class UserStorage {

    public static final int MIN_LENGTH_EMAIL = 5;
    private Set<User> logInUsers = new HashSet<User>();
    private Set<User> createdUser = new HashSet<User>();

    void logIn(String emailAddress, String password) throws UserException{
        User user = new User(emailAddress, password);
        if (!isLogIn(user) && isValidEmail(emailAddress) && isValidPassword(password))
            this.logInUsers.add(user);
        throw new UserException("Invalid email address or password");
    }

    public void logOut(User user) throws UserException {
        if (user != null && isLogIn(user) && this.logInUsers.contains(user))
            this.logInUsers.remove(user);
        throw new UserException("Wrong user");
    }

    public void listAllLogInUsers() {
        for (User user : this.logInUsers) {
            System.out.println("The list is: " + user);
        }
    }

    public void setCreatedUser(User user) {
        this.createdUser.add(user);
    }

    public void removeCreatedUser(User user) {
        this.createdUser.remove(user);
    }

    public boolean isLogIn(User user) {
        return this.logInUsers.contains(user);
    }

    private boolean isValidPassword(String password) {
        if (password != null && password.trim().length() > 0)
            return true;
        return false;
    }

    private boolean isValidEmail(String emailAddress) {
        if(emailAddress != null && emailAddress.contains("@") && emailAddress.trim().length() > MIN_LENGTH_EMAIL)
            return true;
        return false;
    }

}
