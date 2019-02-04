package com.company;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Room {
// Booking
	private class Booking {
		private LocalDate startDate;
		private LocalDate endDate;
		private String user;
		
		private Booking(LocalDate startDate, LocalDate endDate, String user) {
			if (user != null) {
				this.startDate = startDate;
				this.endDate = endDate;
				this.user = user;
			}
		}

		public LocalDate getStartDate() {
			return startDate;
		}

		public LocalDate getEndDate() {
			return endDate;
		}

		public String getUser() {
			return user;
		}
		

		@Override
		public boolean equals(Object obj) {
			if ((obj != null)&&(obj instanceof Booking)) {
				Booking b = ((Booking) obj);
				return (this.getStartDate().isAfter(b.getStartDate()) && this.getStartDate().isBefore(b.getEndDate()))
						||(this.getEndDate().isAfter(b.getStartDate()) && this.getEndDate().isBefore(b.getEndDate()))
						||(this.getStartDate().isBefore(b.getStartDate()) && this.getEndDate().isAfter(b.getStartDate()))
						||(this.getStartDate().isBefore(b.getEndDate()) && this.getEndDate().isAfter(b.getEndDate()))
						||(this.getStartDate().isEqual(b.getStartDate()) || (this.getStartDate().isEqual(b.getEndDate()))
						||(this.getEndDate().isEqual(b.getStartDate()))||(this.getEndDate().isEqual(b.getEndDate())));
			}
			return true;
		}

		@Override
		public String toString() {
			return "Booking [startDate=" + startDate + ", endDate=" + endDate + ", user=" + user + "]";
		}
		
	}

//	Rooms
	private Set<Booking> bookedRooms = new TreeSet<Booking>((b1,b2) -> (b1.getStartDate().compareTo(b2.getStartDate())));
	private String owner;
	private int price;
	
	private Room(String owner,int price) {
			this.owner = owner;
			this.price = price;
	}
	public void reserve(LocalDate startDate, LocalDate endDate, String user) {
		if (user != null) {
			if (startDate.isAfter(endDate)) {
				LocalDate temp = LocalDate.from(startDate);
				startDate = endDate;
				endDate = temp;
			}
			if (user.equalsIgnoreCase(owner)) {
				System.out.println(this.owner + " you are the owner you can't book the room");
				return;
			}
			Booking book = new Booking(startDate, endDate, user);
			for(Booking b :bookedRooms) {
				if (b.equals(book)){
					System.out.println("Already booked " + book);
					return;
				}
			}
			bookedRooms.add(book);
		}
	}

	public Room hostRoom(String owner,int price) {
		if ((owner != null)&&(price >=0 )) {
			return new Room(owner, price);
		} else {
			return new Room("",0);
		}
	}
	
	private int roomCost(LocalDate startDate, LocalDate endDate) {
		return startDate.compareTo(endDate)*this.price;
	}

	@Override
	public String toString() {
		return "Rooms [bookedRooms=" + bookedRooms + "]";
	}
	
	
}
