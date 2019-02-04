package com.company;

import java.util.Map;

public abstract class UserStorage {


    public static final int MIN_LENGTH_EMAIL = 5;
    // Create user -> has the user entered (true or false)
    private Map<User, Boolean> createAndLogIn;

    void logIn(String emailAddress, String password) throws UserException{
        User user = new User(emailAddress, password);
        if (user.getisLogIn() == false && isValidEmail(emailAddress) && isValidPassword(password))
            this.createAndLogIn.put(user, true);
        throw new UserException("Invalid email address or password");
    }

    public void logOut(User user) throws UserException {
        if (user != null && user.getisLogIn() == true && this.createAndLogIn.containsKey(user))
        user.setLogIn(false);
        throw new UserException("Wrong user");
    }

    public void listAllLogInUsers() {
        for (Map.Entry<User, Boolean> entry : this.createAndLogIn.entrySet()) {
            User user = entry.getKey();
            System.out.println("The list is: " + user);
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
        if(emailAddress != null && emailAddress.contains("@") && emailAddress.trim().length() > MIN_LENGTH_EMAIL)
            return true;
        return false;
    }

}
