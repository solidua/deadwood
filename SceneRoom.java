public class SceneRoom extends Room {
	private int shotCount; 
	private Card currentCard;
  private Role roleOne;
  private Role roleTwo;
  private Role roleThree;
  private Role roleFour; 
	
	public SceneRoom(String name, String type) {
		super(name, type); 
	}
	public SceneRoom(String name, String type, int shots, Role firstRole, Role secondRole, Role thirdRole, Role fourthRole) {
		super(name, type);
    shotCount = shots;
    roleOne = firstRole;
    roleTwo = secondRole;
    roleThree = thirdRole;
    roleFour = fourthRole; 
	}
   
   public int getBudget(){
       return currentCard.getBudget();
   }

	public int getShotCount() {
		return shotCount; 
	}
	
	public void decrementShotCount() {
		shotCount--; 
	}
}
