//package deadwood1;

public class Room {
	protected String roomName;  
	protected String roomType; 
	private Room[] adjacentRooms; 

	public Room() {
		roomName = null; 
		roomType = null; 
	}

	public Room(String name, String type) {
		roomName = name; 
		roomType = type; 
	}

	public Room(String name, String type, Room[] adjacent) {
		roomName = name; 
		roomType = type;
		adjacentRooms = adjacent; 
	}

	public String getName() {
		return roomName;
	}

	public Room[] getAdjacentRooms() {
		return adjacentRooms; 
	}

	public void setAdjacentRooms(Room[] adjacent) {
		adjacentRooms = adjacent;
	}

	public String getRoomType() {
		return roomType; 
	}
}
