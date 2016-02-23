/* A2 CS345
*  Room.java
*  Has two children: SceneRoom and UtilityRoom
*  Holds adjacent rooms, and the name of the room.
*/


public class Room {
	protected String roomName;  
	protected String roomType; 
	private Room[] adjacentRooms; 
   
   //constructors
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

   //getters and setters
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
