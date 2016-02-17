package deadwood1;

public class SceneRoom extends Room {
	private int shotCount; 
	private Card currentCard; 
	
	public SceneRoom(String name, String type) {
		super(name, type); 
	}
	
	public SceneRoom(String name, String type, Room[] adjacent) {
		super(name, type, adjacent); 
	}
	
	public int getShotCount() {
		return shotCount; 
	}
	
	public void decrementShotCount() {
		shotCount--; 
	}
}
