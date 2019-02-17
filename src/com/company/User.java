package com.company;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.sun.java_cup.internal.runtime.Scanner;

public class User implements ICreateRemoveUser {
	public static final int MIN_LENGTH_EMAIL = 5;
	public static final String GENDER_MAN = "Male";
	public static final String GENDER_WOMEN = "Female";

	private String firstName;
	private String lastName;
	private String gender;
	private LocalDate birthDay;
	private String emailAddress;
	private String phoneNumber;
	private String prefereredLanguage;
	private String prefereredCurrency;
	private String whereYouLive;
	private String describeYourself;

	private String password;
	private String profilPhoto;
	private double balanceMoney;

	private Set<Room> owned = new HashSet<Room>();
	private Set<Room> booked = new HashSet<Room>();
	private UserStorage userStorage;
	private UserOptionalInfo optionalInfo;
	private Set<Room> favourites = new HashSet<Room>();

	public User(String emailAddress, String password) throws UserException {
		setEmailAddress(emailAddress);
		setPassword(password);
	}

	public User(String emailAddress, String firstName, String lastName, String password) throws UserException {
		setEmailAddress(emailAddress);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
	}

	public void addBookedRoom(Room room) throws RoomException {
		if (room != null) {
			this.booked.add(room);
		} else {
			throw new RoomException("Incorrect room!");
		}
	}
	public void removeBookedRoom(Room room) throws RoomException {
		if (room != null && this.booked.contains(room)) {
			this.booked.remove(room);
		} else {
			throw new RoomException("Incorrect room to remove!");
		}
	}
	
	public void addOwnedRoom(Room room) throws RoomException {
		if (room != null) {
			this.owned.add(room);
		} else {
			throw new RoomException("Incorrect room!");
		}
	}

	public void removeOwnedRoom(Room room) throws RoomException {
		if (room != null && this.owned.contains(room)) {
			this.owned.remove(room);
		} else {
			throw new RoomException("Incorrect room to remove!");
		}
	}

	public void addFavourite(Room room) throws RoomException {
		if (room != null) {
			this.favourites.add(room);
		} else {
			throw new RoomException("Incorrect room!");
		}

	}

	public void removeFavourite(Room room) throws RoomException {
		if (room != null && this.favourites.contains(room)) {
			this.favourites.remove(room);
		} 
	}
	public void showOwned() {
		for(Room r : owned) {
			System.out.print(r);
		}
	}
	public void showBooked() {
		for(Room r : booked) {
			System.out.print(r);
		}
	}
	
	public void showFavourite() {
		for(Room r : favourites) {
			System.out.print(r);
		}
	}

	public void editProfil() throws UserException {
		setFirstName(new String());
		setLastName(new String());
		setGender(new String());
		setEmailAddress(new String());
		setPrefereredLanguage(new String());
		setPrefereredCurrency(new String());
		setDescribeYourself(new String());

	}

	public double getBalanceMoney() {
		return balanceMoney;
	}

	private void setLastName(String lastName) throws UserException {
		if (lastName != null && lastName.trim().length() > 0) {
			this.lastName = lastName;
		} else {
			throw new UserException("Invalid last name!");
		}
	}

	private void setFirstName(String firstName) throws UserException {
		if (firstName != null && firstName.trim().length() > 0) {
			this.firstName = firstName;
		} else {
			throw new UserException("Invalid first name!");
		}
	}

	private void setPassword(String password) throws UserException {
		if (password != null && password.trim().length() > 0) {
			this.password = password;
		} else {
			throw new UserException("Invalid passward!");
		}
	}

	private void setEmailAddress(String emailAddress) throws UserException {
		if (emailAddress != null && emailAddress.contains("@") && emailAddress.trim().length() > MIN_LENGTH_EMAIL) {
			this.emailAddress = emailAddress;
		} else {
			throw new UserException("Invalid eamil address!");
		}
	}

	public void setGender(String gender) throws UserException {
		if (gender != null && gender.trim().length() > 0
				&& (gender.equals(GENDER_MAN) || gender.equals(GENDER_WOMEN))) {
			this.gender = gender;
		} else {
			throw new UserException("Invalid gender!");
		}
	}

	public void setPhoneNumber(String phoneNumber) throws UserException {
		if (phoneNumber != null && phoneNumber.trim().length() > 0 && phoneNumber.contains("[\\d+]")) {
			this.phoneNumber = phoneNumber;
		} else {
			throw new UserException("Invalid phone number!");
		}
	}

	public void setPrefereredLanguage(String prefereredLanguage) throws UserException {
		if (prefereredLanguage != null && prefereredLanguage.trim().length() > 0) {
			this.prefereredLanguage = prefereredLanguage;
		} else {
			throw new UserException("Invalid preferred language!");
		}
	}

	public void setPrefereredCurrency(String prefereredCurrency) throws UserException {
		if (prefereredCurrency != null && prefereredCurrency.trim().length() > 0) {
			this.prefereredCurrency = prefereredCurrency;
		} else {
			throw new UserException("Invalid preferred currency!");
		}
	}

	public void setWhereYouLive(String whereYouLive) throws UserException {
		if (whereYouLive != null && whereYouLive.trim().length() > 0) {
			this.whereYouLive = whereYouLive;
		} else {
			throw new UserException("Invalid user's address!");
		}
	}

	public void setDescribeYourself(String describeYourself) throws UserException {
		if (describeYourself != null && describeYourself.trim().length() > 0) {
			this.describeYourself = describeYourself;
		} else {
			throw new UserException("Invalid text!");
		}
	}

	public void setProfilPhoto(String profilPhoto) throws UserException {
		if (profilPhoto != null && profilPhoto.trim().length() > 0) {
			this.profilPhoto = profilPhoto;
		} else {
			throw new UserException("Invalid photo!");
		}
	}

	public void setBalanceMoney(double balanceMoney) {
		this.balanceMoney = balanceMoney;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof User)
			return this.emailAddress.equals(((User) obj).emailAddress);
		return false;
	}

	@Override
	public int hashCode() {
		return this.emailAddress.hashCode() * this.password.hashCode();
	}

	@Override
	public String toString() {
		return "[User " + this.firstName + " " + this.lastName + "]";
	}

	@Override
	public User signUpWithEmailAddress(String emailAddress, String firstName, String lastName, String password)
			throws UserException {
		User user = new User(emailAddress, firstName, lastName, password);
		userStorage.setCreatedUser(user);
		return user;
	}

	@Override
	public User signUpWithFacebook(String facebookName, String password) throws UserException {
		User user = new User(facebookName, password);
		userStorage.setCreatedUser(user);
		return user;
	}

	@Override
	public User signUpWithGoogle(String googleAddress, String password) throws UserException {
		User user = new User(googleAddress, password);
		userStorage.setCreatedUser(user);
		return user;
	}

	@Override
	public void removeAccount(User user) throws UserException {
		if (user != null) {
			userStorage.removeCreatedUser(user);
		} else {
			throw new UserException("Invalid user to remove");
		}
	}
}
