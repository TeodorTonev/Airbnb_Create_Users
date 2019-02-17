package com.company;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
	
	private static final int END = 0;

	static void menu() throws UserException, RoomException, PictureException {
		Scanner sc = new Scanner(System.in);
		UserStorage us = UserStorage.getInstance();
    	RoomStorage rs = RoomStorage.getInstance();
    	
    	int i = 0;
    	
    	while(i!= END) {
    		System.out.println("Welcome" + "\nPlease chose from the options bellow " + "\n1: Log in" + "\n2: Register" + "\n0: exit");
    		System.out.println("Enter the number from above to chose");
    		i = sc.nextInt();
    		switch (i) {
			case 1:
				System.out.println("Enter email and password in that order");
				User u = us.logIn(sc.next(), sc.next());
				if(us.isLoggedIn(u)){
					userMenu(u);
				}
				break;
			case 2:
				System.out.println("Enter email, first name, second name and password in that order");
				us.register(sc.next(), sc.next(), sc.next(), sc.next());
				System.out.println("Registration complete pleace log in");
				break;
			case 0:
				System.out.println("Exiting");
				return;
			}
    		
    	}
	}

	public static void userMenu(User user) throws UserException, RoomException, PictureException {
		Scanner sc = new Scanner(System.in);
		UserStorage us = UserStorage.getInstance();
    	RoomStorage rs = RoomStorage.getInstance();
    	
		int i = -1;
    	
    	while(i != END) {
    		System.out.println("Welcome" + user.toString() + "\nPlease chose from the options bellow " + "\n1: Select room"  
    				+ "\n2: Search rooms by city" + "\n3: Your booked rooms" + "\n4: Your favourite rooms" + "\n5: Your hosted rooms" + "\n6: Host room"
    				+ "\n0: Log out");
    		System.out.println("Enter the number from above to chose");
    		i = sc.nextInt();
    		switch (i) {
    		case 1:
				System.out.println("Enter city and address");
				roomOptions(rs.getRoom(sc.next(), sc.next()), user);
				break;
			case 2:
				System.out.println("Enter city");
				rs.listByCity(sc.next());
				break;
			case 3:
				System.out.println("Booked:");
				user.showBooked();
				break;
			case 4:
				System.out.println("Favourite:");
				user.showFavourite();
				break;
			case 5:
				System.out.println("Hosted:");
				user.showOwned();
				break;
			case 6:
				System.out.println("Enter city , price , address, guests, beds, baths and type of building");
				rs.hostRoom(Room.createRoom(sc.next(), user, sc.nextInt(), sc.next(), sc.nextInt(), sc.nextInt(), sc.nextInt(),
						sc.nextLine()));
				break;
			case 0:
				return;
			}
    		
    	}
		 
	}
	
	static void roomOptions(Room room, User user) throws RoomException, PictureException {
		Scanner sc = new Scanner(System.in);
		UserStorage us = UserStorage.getInstance();
    	RoomStorage rs = RoomStorage.getInstance();
    	boolean isOwner = room.getOwner().equals(user);
    	
		int i = -1;
    	
    	while(i!= END) {
    		System.out.println("Welcome" + user.toString() + "\nPlease chose from the options bellow " + "\n1: Add to favourites"  
    				+ "\n2: remove from favourites" + "\n3: Reserve room");
    		    	if(isOwner) {
    		    		System.out.println ("4: Add pictures of the room" + "\n5: Remove room");
    		    	}
    		System.out.println("0: Back" + "\nEnter the number from above to chose");
    		i = sc.nextInt();
    		switch (i) {
    		case 0:
				return;
    		case 1:
				System.out.println("Adding to fav");
				user.addFavourite(room);
				break;
			case 2:
				System.out.println("Removing from fav");
				user.removeFavourite(room);
				break;
			case 3:
				System.out.println("Enter start date(year,month.day) and end date(year,month,day)");
				room.reserve(LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt()), LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt()), user);
				break;
			}
    		
    		if(isOwner) {
    			switch (i) {
    			case 4:
    				System.out.println("Enter files (string) room");
    				room.addPicture(sc.nextLine());
    				break;
    			case 5:
    				System.out.println("Removing room");
    				rs.removeRoom(room);
    				break;
				}
    		}
    		
    	}
	}
    public static void main(String[] args) throws Exception {
//    	UserStorage us = UserStorage.getInstance();
//    	RoomStorage rs = RoomStorage.getInstance();
//    	
//    	us.register("hose@hose.bg", "hose", "hose", "asdf");
//    	us.register("pesho@pesho.bg", "pesho", "pesho", "asdf");
//    	rs.hostRoom(Room.createRoom("sofia", us.logIn("hose@hose.bg", "asdf"), 22, "ss", 2, 2, 2, "app"));
    	
    	
    	menu();
    	
    }
}
