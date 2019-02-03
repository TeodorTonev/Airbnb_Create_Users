package com.company;

public interface ICreateRemoveUser {

    public User signUpWithEmailAddress(String emailAddress, String firstName,
                                       String lastName, String password) throws UserException;

    public User signUpWithFacebook(String facebookName, String password) throws UserException;

    public User signUpWithGoogle(String googleAddress, String password) throws UserException;

    public void removeAccount(User user) throws UserException;
}
