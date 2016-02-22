package deadwood1;

public class SceneRoom extends Room {
	
	//Need "get roles" function, to get the roles in this room and the roles on the card as well hopefully, return as array or somesuch
	private int shotCount; 
	private Card currentCard;
   private Role roleOne;
   private Role roleTwo;
   private Role roleThree;
   private Role roleFour;
   private boolean beenVisited = false;
   //Last three cards initialized when gotVisited() is called
   private Role[] allRoles = {roleOne, roleTwo, roleThree, roleFour, null, null, null};
	
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
   
   public Role[] gotVisited(){
      if(!beenVisited){
         beenVisited = true;
         Role[] cardRoles = currentCard.getRoles();
         int numCardRoles = cardRoles.length();
         for(int i = 0; i < numCardRoles; i++){
            allRoles[i+4] = cardRoles[i];
         }
         System.out.println("This is the " super(roomName));
         System.out.println("The scene for this room is " currentCard.getTitle);
         System.out.println(currentCard.getDescription);
      }
      return allRoles;
   }
   
   public Card setCard(Card newCard){
      return currentCard = newCard;
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
