<<<<<<< HEAD

=======
package deadwood1;
>>>>>>> 986beb67bc4893f11263ac7c17db6e08fa1a0bdc

public class Room {
	private String roomName;  
	private String roomType; 
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
<<<<<<< HEAD
  
  public void setAdjacentRooms(Room[] adjacent) {
      adjacentRooms = adjacent;
  }
=======
>>>>>>> 986beb67bc4893f11263ac7c17db6e08fa1a0bdc

	public String getRoomType() {
		return roomType; 
	}
}
