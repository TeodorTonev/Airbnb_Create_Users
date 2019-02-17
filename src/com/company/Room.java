package com.company;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Room {
// Booking
	private class Booking {
		private LocalDate startDate;
		private LocalDate endDate;
		private User user;

		private Booking(LocalDate startDate, LocalDate endDate, User user) {
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

		public User getUser() {
			return user;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Booking other = (Booking) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (endDate == null) {
				if (other.endDate != null)
					return false;
			} else if (!endDate.equals(other.endDate))
				return false;
			if (startDate == null) {
				if (other.startDate != null)
					return false;
			} else if (!startDate.equals(other.startDate))
				return false;
			if (user == null) {
				if (other.user != null)
					return false;
			} else if (!user.equals(other.user))
				return false;
			return true;
		}

		public boolean overlap(Booking b) {
			if (b != null) {
				return (this.getStartDate().isAfter(b.getStartDate()) && this.getStartDate().isBefore(b.getEndDate()))
						|| (this.getEndDate().isAfter(b.getStartDate()) && this.getEndDate().isBefore(b.getEndDate()))
						|| (this.getStartDate().isBefore(b.getStartDate())
								&& this.getEndDate().isAfter(b.getStartDate()))
						|| (this.getStartDate().isBefore(b.getEndDate()) && this.getEndDate().isAfter(b.getEndDate()))
						|| (this.getStartDate().isEqual(b.getStartDate())
								|| (this.getStartDate().isEqual(b.getEndDate()))
								|| (this.getEndDate().isEqual(b.getStartDate()))
								|| (this.getEndDate().isEqual(b.getEndDate())));
			}
			return true;
		}

		@Override
		public String toString() {
			return "Booking [startDate=" + startDate + ", endDate=" + endDate + ", user=" + user + "]";
		}

		private Room getOuterType() {
			return Room.this;
		}

	}

//	Rooms
	private Set<Booking> booked;
	private Set<String> pictures;
	private User owner;
	private int price;
	private String address;
	private int guests;
	private int beds;
	private int baths;
	private String type;
	private String city;

	public Room(String city, User owner, int price, String address, int guests, int beds, int baths, String type) {
		this.booked = new TreeSet<Booking>((b1, b2) -> (b1.getStartDate().compareTo(b2.getStartDate())));
		this.pictures = new HashSet<String>();
		this.owner = owner;
		this.price = price;
		this.address = address;
		this.guests = guests;
		this.beds = beds;
		this.baths = baths;
		this.type = type;
		this.city = city.toLowerCase();
	}

	public void reserve(LocalDate startDate, LocalDate endDate, User user) throws RoomException {
		if (user != null) {
			if (startDate.isAfter(endDate)) {
				LocalDate temp = LocalDate.from(startDate);
				startDate = endDate;
				endDate = temp;
			}
			if (user.equals(owner)) {
				throw new RoomException("You can't book your own room.");
			}
			Booking book = new Booking(startDate, endDate, user);
			for (Booking b : booked) {
				if (b.overlap(book)) {
					throw new RoomException("Room already booked.");
				}
			}
			if ((user.getBalanceMoney() < this.roomCost(startDate, endDate))) {
				throw new RoomException("Insufficient money");
			}
			user.setBalanceMoney(user.getBalanceMoney() - this.roomCost(startDate, endDate));
			booked.add(book);
			user.addBookedRoom(this);
			this.owner.setBalanceMoney(this.owner.getBalanceMoney() + this.roomCost(startDate, endDate));
		}
	}

	public static Room createRoom(String city, User owner, int price, String address, int guests, int beds, int baths, String type)
			throws RoomException {
		if ((owner != null) && (price >= 0) && (address != null) && (type != null) && (guests > 0) && (beds > 0)
				&& (baths > 0)) {
			Room room = new Room( city,  owner,  price,  address,  guests,  beds,  baths,  type);
			owner.addOwnedRoom(room);
			return room;
		} else {
			throw new RoomException("Invalid information");
		}
	}

	private int roomCost(LocalDate startDate, LocalDate endDate) {
		return startDate.compareTo(endDate) * this.price;
	}

	public void addPicture(String pic) throws PictureException {
		if (pic != null) {
			this.pictures.add(pic);
		} else {
			throw new PictureException("Invalid format");
		}
	}

	public void removePicture(String pic) throws PictureException {
		if (pic != null) {
			this.pictures.remove(pic);
		} else {
			throw new PictureException("Invalid format");
		}
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if ((obj != null)&&(obj instanceof Room)) {
			Room r = (Room)obj;
			return this.address.equalsIgnoreCase(r.getAddress()) && (this.city.equalsIgnoreCase(r.getCity()));
		}
		return true;
	}

	public Set<Booking> getBooked() {
		return booked;
	}

	public Set<String> getPictures() {
		return pictures;
	}

	public User getOwner() {
		return owner;
	}

	public int getPrice() {
		return price;
	}

	public String getAddress() {
		return address;
	}

	public int getGuests() {
		return guests;
	}

	public int getBeds() {
		return beds;
	}

	public int getBaths() {
		return baths;
	}

	public String getType() {
		return type;
	}

	public String getCity() {
		return city;
	}

	@Override
	public String toString() {
		return "\nRoom [ owner=" + owner + ", price=" + price
				+ ", address=" + address + ", guests=" + guests + ", beds=" + beds + ", baths=" + baths + ", type="
				+ type + ", city=" + city + "booked=" + booked + ", pictures=" + pictures + "]\n";
	}


}
