package deadwood1;

import java.util.ArrayList;
import java.util.List;

public class SceneRoom extends Room {
	
   private int shotCount; 
   private Card currentCard;
   private Role[] roomRoles; 
	
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
	
	public Role[] getRoomRoles() {
		return roomRoles; 
	}

	public Role[] getCardRoles() {
		return currentCard.getRoles(); 
	}	
	   
	public void displayInfo() {
		System.out.println(roomName + " shooting " + currentCard.getTitle());
	}
	
	public void displayRoles(int rank) {
		Role[] cardRoles = currentCard.getRoles();
		System.out.println("These are the starring roles you can take:");
		for(Role role : cardRoles) {         
			if(!role.getTaken() && (role.getLevel() <= rank)) {
				System.out.println("\t" + role.getName());
			} 
		}
		
		System.out.println("These are the extra roles you can take:");
		for(Role role: roomRoles) {
			if(!role.getTaken() && (role.getLevel() <= rank)) {
				System.out.println("\t" + role.getName());
			} 
		}	
	}
	
	public Role takeRole(String roleWanted) {
		Role[] cardRoles = currentCard.getRoles();
		for(Role role : cardRoles) {
			if(role.getName().toLowerCase().equals(roleWanted)) {
				if(role.getTaken()) {
					System.out.println("Sorry this role has already been taken");
					return null; 
				} else {
					role.setTaken(true);
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
					role.setTaken(true);
					System.out.println("You have taken the extra role of " + role.getName());
					return role; 
				}
			}
		}
		
		System.out.println("You did not enter a valid role name. Type 'work' to list the available roles");
		return null; 
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
}
