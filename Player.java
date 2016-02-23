//package deadwood1;

import java.util.*;
import java.lang.*;

public class Player implements Dice{
	private String name; 
	private int rank = 1;
	private int money;
	private int credits;
	private boolean acting = false;
	private int rehearsalCount = 0;
	private Room position;
	private boolean turnOver = false;
	private Role currentRole = null; 
	
	//Constructors
	public Player(int startCredits, int startRank, Room trailers, String name){
		this.name = name; 
		rank = startRank;
		money = 0;
		credits = startCredits;
		position = trailers;
	}
	
	public Player(Room trailers, String name){
		this.name = name; 
		rank = 1;
		credits = 0;
		money = 0;
		position = trailers;
	}

	//Getters and setters
	public String getName() {
		return name; 
	}
	
	public int getRank(){
		return rank;
	}
	
	public void setRank(int newrank){
		this.rank = newrank;
	}
	
	public int getMoney() {
		return money;
	}
	
	public int addMoney(int pay){
		return (this.money + pay);
	}
	
	public int getCredits() {
		return credits; 
	}
	
	public int addCredit(int pay){
		return (this.credits + pay);
	}
	
	public boolean isActing(){
		return acting;
	}
	
	public void setActing(boolean input){
		acting = input;
	}
	
	public void setPosition(Room room) {
		position = room; 
	}
	
	public Room getPosition(){
		return position;
	}
	
	public void who(){
		System.out.print(name + " " + "($" + money + "," + credits + "cr" + ")");
		if(currentRole != null) {
			System.out.println(" working " + currentRole.getName() + ", " + currentRole.getPhrase());
		} else {
			System.out.println(); 
		}
	}
	
	public void where() {
		System.out.print("in ");
		if(position.getRoomType().equals("Scene")) {
			SceneRoom scene = (SceneRoom) position;
			scene.displayInfo(); 
		} else {
			UtilityRoom util = (UtilityRoom) position; 
			String name = util.getName(); 
			System.out.println(name);
		}
	}

	public boolean move(String dest){
		turnOver = true;
		if(!acting) {
			Room[] moveOptions = this.position.getAdjacentRooms();
			//display adjacent rooms 
			if (dest.equals("none")) {
				System.out.println("The adjacent rooms you can move to are:");
				for(int i = 0; i < moveOptions.length; i++){
					System.out.println("\t" + moveOptions[i].getName());
				}
				System.out.println("Type 'move' and the room you would like to move to (ie. 'move Saloon')"); 
				return false; 
			} else {
				for(int i = 0; i < moveOptions.length; i++){
					if(moveOptions[i].getName().toLowerCase().equals(dest)){
						position = moveOptions[i]; 
						System.out.println("You have moved to " + moveOptions[i].getName());
						return true; 
					}     
				}
				System.out.println("That is not a valid room. Type 'move' to list the rooms you can move to");
				return false; 
			}
		} else {
			System.out.println("You can't move because you are currently acting.");
			return false; 
		}

	}
	
	public boolean work(String roleWanted) {
		if(!acting) {
			if (position.getRoomType().equals("Scene")) {
				SceneRoom currentScene = (SceneRoom)position;
				if(currentScene.getShotCount() == 0) {
					System.out.println("this scene is finished");
					return false; 
				} else {
					if(roleWanted.equals("none")) {
						currentScene.displayRoles(rank); 
						return false; 
					} else {
						acting = true; 
						rehearsalCount = 0; 
						currentRole = currentScene.takeRole(roleWanted, this);
						return true; 
					}
				}
			} else {
				UtilityRoom currentUtil = (UtilityRoom)position; 
				System.out.println("You are in " + currentUtil.getName() + ". You cannot take a role here.");
				return false; 
			}
		} else {
			System.out.println("You have already taken up a role.");
			return false; 
		}
	}
	
	public boolean rehearse(){
		if(acting){
			rehearsalCount++; 
			System.out.println("You have rehearsed for your role. Your rehearsal count is now " + rehearsalCount + ".");
			return true; 
		}else{
			System.out.println("You cannot rehearse because you haven't taken up a role yet.");
			return false; 
		}     
	}
	
	public int act() {
		if (acting) {
			SceneRoom scene = (SceneRoom) position; 
			int[] dieOutcomes = rollDice(1); 
			int budget = scene.getBudget(); 
			System.out.println("The budget for this scene is " + budget);
			System.out.println("You rolled a " + dieOutcomes[0]);
			System.out.println("You rehearsal count is " + rehearsalCount);
			if (dieOutcomes[0] + rehearsalCount >= budget) {
				System.out.println("You have succeded!"); 
				int shotsLeft = scene.decrementShotCount(); 
				if (shotsLeft > 0) {
					System.out.println("There are " + shotsLeft + " shots left.");
					if(currentRole.getOnCard()) {
						System.out.println("You recieved 2 credits");
						credits = credits + 2; 
					} else {
						System.out.println("You recieved 1 credit and 1 dollar");
						credits++; 
						money++; 
					}
					return 1; 
				} else {
					System.out.println("You have completed the scene!");
					acting = false; 
					int[] diceOutcomes = rollDice(scene.getBudget()); 
					scene.distributeBonuses(diceOutcomes); 
					return 2; 
				}
			} else {
				System.out.println("You have failed."); 
				if(!currentRole.getOnCard()) {
					money++; 
				}
				return 1; 
			}
		} else {
			System.out.println("You cannot act because you haven't taken up a role yet.");
			return 0; 
		}
	}
	
//	public boolean upgrade(String type, int level) {
//		if(position.getName().equals("Casting Office")) {
//			UtilityRoom util = (UtilityRoom) position; 
//			if(level > 1) {
//				return util.tryUpgrade(level);
//			} else {
//				util.displayPossible(); 
//				return false; 
//			}
//		} else {
//			System.out.println("You need to be in the casting office to upgrade your level");
//			return false; 
//		}
//	}
	

	//ask player if they want to pay with money or credit
	//   public int upgrRank(int newrank){
	//      boolean inputGood = false;
	//      Scanner scanInput = new Scanner(System.in);      
	//      if(this.position.getRoomType().equals("Casting Office")){
	//        System.out.println("Do you want to pay for your new rank with money or credit?");
	//        System.out.print("2 is $4 or 5 credits, 3 is $10 or 10 credits, 4 is $18 or 15 credits, 5 is $28 dollars or 20 credits, 6 is $40 or 25 credits");
	//        while(!inputGood){
	//          System.out.println("You currently have " + credits + " credits and " + money + " dollars.");
	//          String input = getInput();
	//          UtilityScene castingOffice = (UtilityScene)position;
	//          if(input.equals("money") && (this.money > castingOffice.improveRank(newrank, "money"))){
	//            setRank(newrank);
	//            inputGood = true;
	//          }else if(input.equals("credit") && (this.credits > castingOffice.improveRank(newrank, "credit"))){
	//            setRank(newrank);
	//            inputGood = true;
	//          }else if(input.equals("quit")){
	//            setRank(-1);
	//            inputGood = true;
	//          }else if(!input.equals("credit") && !input.equals("money")){
	//            System.out.println("Wrong input! Please input either money or credit for your preferred method of payment!");
	//          }else{
	//            System.out.println("You don't have sufficient money/credit for that! Please choose a different payment method or type 'quit' to stop");
	//          }
	//        }
	//    }
	//    return newrank;
	//  }

	//Helper function, get string from standard in
	private String getInput(){
		Scanner scanInput = new Scanner(System.in);
		String input = scanInput.nextLine();
		scanInput.close();
		return input.toLowerCase();
	}  

	public int[] rollDice(int numDice){
		Random d6 = new Random();
		int[] values = new int[numDice];
		for(int i = 0; i < numDice; i++){
			values[i] = d6.nextInt(6) + 1;         
		}
		return values;
	}

}
