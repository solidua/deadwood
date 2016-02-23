/* A2 CS345
*  SceneRoom.java
*  Inherits from Room Class.
*  Holds scene information, a Card, and extra Roles.
*/


public class SceneRoom extends Room {

	private int shotCount; 
	private Card currentCard;
	private Role[] roomRoles;

   //constructors
	public SceneRoom(String name, String type) {
		super(name, type); 
	}

	public SceneRoom(String name, String type, int shots, Role firstRole, Role secondRole) {
		roomName = name; 
		roomType = type; 
		shotCount = shots;
		roomRoles = new Role[]{firstRole, secondRole}; 
	}

	public SceneRoom(String name, String type, int shots, Role firstRole, Role secondRole, Role thirdRole) {
		roomName = name; 
		roomType = type; 
		shotCount = shots;
		roomRoles = new Role[]{firstRole, secondRole, thirdRole}; 
	}

	public SceneRoom(String name, String type, int shots, Role firstRole, Role secondRole, Role thirdRole, Role fourthRole) {
		roomName = name; 
		roomType = type; 
		shotCount = shots;
		roomRoles = new Role[]{firstRole, secondRole, thirdRole, fourthRole}; 
	}
   
   //getters and setters
	public Role[] getRoomRoles() {
		return roomRoles; 
	}

	public Role[] getCardRoles() {
		return currentCard.getRoles(); 
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

	public int decrementShotCount() {
		shotCount--; 
		return shotCount; 
	}

	public boolean isCardRoleTaken() {
		return currentCard.isARoleTaken(); 
	}

   public void displayInfo() {
		System.out.println(roomName + " shooting " + currentCard.getTitle());
	}
   
   /* Pre: Accepts the rank of the Player who is requesting what Roles are available.
   *  Post: Prints out the available Roles to the requesting Player, printing to System.out
   */
	public void displayRoles(int rank) {
		Role[] cardRoles = currentCard.getRoles();
      System.out.println("The scene's budget is: $" + currentCard.getBudget() + " million dollars, and has " + shotCount + " shots left.");
		System.out.println("These are the starring roles you can take:");
      boolean roleAvailable = false;
		for(Role role : cardRoles) {         
			if(!role.getTaken() && (role.getLevel() <= rank) && !(role.getName().equals("0"))) {
				System.out.println("\t" + role.getName() + ": \"" + role.getPhrase()+ "\" which is level " + role.getLevel());
            roleAvailable = true;
			}
		}
      if(!roleAvailable){
         System.out.println("\t" + "There are no starring roles you can take!");
      }

		System.out.println("These are the extra roles you can take:");
      roleAvailable = false;
		for(Role role: roomRoles) {
			if(!role.getTaken() && (role.getLevel() <= rank) && !(role.getName().equals("0"))) {
				System.out.println("\t" + role.getName() + ": \"" + role.getPhrase()+ "\" which is level " + role.getLevel());
            roleAvailable = true;
			} 
		}
      if(!roleAvailable){
         System.out.println("\t" + "There are no extra roles you can take!");
      }	
	}

   /* Pre:  Accepts a Role the Player can take, and wants to take, and the Player
   *  Post: Returns the Role if successful, null otherwise.
   */
	public Role takeRole(String roleWanted, Player player) {
		Role[] cardRoles = currentCard.getRoles();
		for(Role role : cardRoles) {
			if(role.getName().toLowerCase().equals(roleWanted)) {
				if(role.getTaken()) {
					System.out.println("Sorry this role has already been taken");
					return null; 
				} else {
					role.setTaken(true, player);
					System.out.println("You have taken the starring role of " + role.getName());
					return role; 
				}
			}
		}

		for(Role role : roomRoles) {
			if(role.getName().toLowerCase().equals(roleWanted)) {
				if(role.getTaken()) {
					System.out.println("Sorry this role has already been taken");
					return null; 
				} else {
					role.setTaken(true, player);
					System.out.println("You have taken the extra role of " + role.getName());
					return role; 
				}
			}
		}

		System.out.println("You did not enter a valid role name. Type 'work' to list the available roles");
		return null; 
	}
   
   /* Pre:  A integer array of the different dice rolls after a scene is completed.
   *  Post: Adds values to the players playing based on the different rolls.
   */
	public void distributeBonuses(int[] diceOutcomes) {
		if(currentCard.isARoleTaken()) {
			System.out.println("Bonuses will be distributed");
			Role[] cardRoles = getCardRoles(); 
			for (int i = 0; i < diceOutcomes.length; i++) {
            if(cardRoles[i % cardRoles.length].getTaken()){
   				cardRoles[i % cardRoles.length].giveBonus(diceOutcomes[i]);
            }
			}

			for (Role role : roomRoles) {
            if(role.getTaken()){
				   role.giveBonus(0);
            }
			}

		} else {
			System.out.println("Nobody held a starring role. Bonuses will not be distributed");
		}
		clearRoles(); 
	}

   /* Pre: Is called when the scene is completed
   *  Post: Removes Players from all the Roles.
   */
	private void clearRoles() {
		Role[] cardRoles = getCardRoles(); 

		for(Role role : cardRoles) {
         if(role.getTaken()){
   			role.removePlayer(); 
         }
		}

		for(Role role : roomRoles) {
         if(role.getTaken()){
   			role.removePlayer(); 
         }      
		}
	}
}
