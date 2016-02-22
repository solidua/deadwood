package deadwood1;

public class Room {
	private String roomName;  
	private String roomType; 
	private String[] adjacentRooms; 

	public Room() {
		roomName = null; 
		roomType = null; 
	}
	
	public Room(String name, String type) {
		roomName = name; 
		roomType = type; 
	}
	
	public Room(String name, String type, String[] adjacent) {
		roomName = name; 
		roomType = type;
		adjacentRooms = adjacent; 
	}
	
	public String getName() {
		return roomName;
	}
	
	public String[] getAdjacentRooms() {
		return adjacentRooms; 
	}
  
  public void setAdjacentRooms(String[] adjacent) {
      adjacentRooms = adjacent;
  }
	public String getRoomType() {
		return roomType; 
	}
}
