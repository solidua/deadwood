<<<<<<< HEAD


public class SceneRoom extends Room {
	private int shotCount; 
	private Card currentCard;
  private Role roleOne;
  private Role roleTwo;
  private Role roleThree;
  private Role roleFour; 
=======
package deadwood1;

public class SceneRoom extends Room {
	private int shotCount; 
	private Card currentCard; 
>>>>>>> 986beb67bc4893f11263ac7c17db6e08fa1a0bdc
	
	public SceneRoom(String name, String type) {
		super(name, type); 
	}
	
<<<<<<< HEAD
	public SceneRoom(String name, String type, int shots, Role firstRole, Role secondRole, Role thirdRole, Role fourthRole) {
		super(name, type);
    shotCount = shots;
    roleOne = firstRole;
    roleTwo = secondRole;
    roleThree = thirdRole;
    roleFour = fourthRole; 
	}
  
=======
	public SceneRoom(String name, String type, Room[] adjacent) {
		super(name, type, adjacent); 
	}
	
>>>>>>> 986beb67bc4893f11263ac7c17db6e08fa1a0bdc
	public int getShotCount() {
		return shotCount; 
	}
	
	public void decrementShotCount() {
		shotCount--; 
	}
}
