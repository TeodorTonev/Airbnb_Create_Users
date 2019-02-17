package com.company;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RoomStorage {
	private Map<String, Set<Room>> rooms;
	private static RoomStorage instance;
	
	private RoomStorage() {
		rooms = new HashMap<>();
	}
	  
	public static RoomStorage getInstance() {
		if (instance == null) {
			instance = new RoomStorage();
		}
		return instance;
	}
    public void hostRoom(Room room) throws RoomException {
    	if(room != null) {
    		Set<Room> r = null;
    		if (!rooms.containsKey(room.getCity())) {
    			r = new HashSet<>();
    			r.add(room);
    		} else {
    			r = this.rooms.get(room.getCity());
    			r.add(room);
    		}
    		this.rooms.put(room.getCity(), r);
    	} else {
    		throw new RoomException("Invalid room");
    	}
    }
    public void removeRoom(Room room) throws RoomException {
    	if (room != null) {
    		Set<Room> r = this.rooms.get(room.getCity());
    		r.remove(room);
    		this.rooms.put(room.getCity(), r);
    	} else {
    		throw new RoomException("Invalid room");
    	}
    }

    public void listByCity(String city) {
    	Set<Room> a = null;
    	for(String s : this.rooms.keySet()) {
    		if (s.equals(city)) {
    			a = this.rooms.get(s);
    			break;
    		}
    	}
    	if((a != null)&&(a.size() != 0)){
    		System.out.println(a);
    	} else {
    		System.out.println("No results found");
    	}
    }
    public Room getRoom(String city, String address) throws RoomException {
    	Set<Room> r = this.rooms.get(city);
    	Room room = r.stream()
    	.filter(t -> {return t.getAddress().equalsIgnoreCase(address) && t.getCity().equalsIgnoreCase(city);})
    	.findFirst()
    	.get();
    	
    	return room;
    }
    
    public void showUserOwnedRooms(User user) {
    	
    }
	@Override
	public String toString() {
		return "RoomStorage [rooms=" + rooms + "]";
	}
    
    
}
