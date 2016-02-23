/* A2 CS345
*  Player.java
*  Stores player related data, rolls dice, and can perform the following player actions: 
*     act, rehearse, move, upgrade rank, and roll dice.
*/
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
	
	//constructors
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

	//getters and setters
	public String getName() {
		return name; 
	}
   
   public void clearRole(){
      currentRole = null;
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
	
	public void addMoney(int pay){
      money = money + pay;
	}
	
	public int getCredits() {
		return credits; 
	}
	
	public void addCredit(int pay){
      credits = credits + pay;  
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
	
   /* Pre: Called when Player wants to know who they are or their status.
   *  Post: Prints the current Player's name, current currencies, and current rank, and what Role they are in, if they have a Role.
   */
	public void who(){
		System.out.print(name + " " + "($" + money + "," + credits + "cr" + ")" + " Rank: " + rank);
		if(currentRole != null) {
			System.out.println(" working " + currentRole.getName() + ", " + currentRole.getPhrase());
		} else {
			System.out.println(); 
		}
	}
	
   /* Pre: Called when Player wants to know where they are.
   *  Post: Prints what room the player is in.
   */
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

   /* Pre: Accepts none or the name of one of the adjacent Rooms.
   *  Post: Prints the adjacent rooms when the input is none, and changes the Player's position to the room they moved to if successful.
   *        Returns false when it did not succeed, and true if it was successful.
   */
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
	
   /* Pre: Accepts the name of one of the roles in the current Room. Must be in a SceneRoom and not acting.
   *  Post: Returns true if it was successful, and false if it was not.
   */
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
	
   /* Pre: Accepts no arguments. Must be acting to complete successfully.
   *  Post: If acting, returns true and increments rehearsalCount by one. Returns false if rehearsalCount is equal to 
   *        the scene's budget. Returns false if not acting.
   */
	public boolean rehearse(){
		if(acting){
         SceneRoom scene = (SceneRoom)position;
         if(scene.getBudget() > rehearsalCount){                     
			   rehearsalCount++; 
			   System.out.println("You have rehearsed for your role. Your rehearsal count is now " + rehearsalCount + ".");
			   return true;
         }else{
            System.out.println("You have rehearsed as much as you can, go ahead and act.");
            return false;
         }
		}else{
			System.out.println("You cannot rehearse because you haven't taken up a role yet.");
			return false; 
		}     
	}
	
   /* Pre: Accepts no arguments. Must be acting.
   *  Post: Returns a number based on what it did, 1 if the scene did not finish, and 2 if the scene did. Should not return -1.
   */
	public int act() {
      int retvalue = -1;
		if (acting) {
			SceneRoom scene = (SceneRoom) position; 
			int[] dieOutcomes = rollDice(1); 
			int budget = scene.getBudget(); 
			System.out.println("The budget for this scene is " + budget);
			System.out.println("You rolled a " + dieOutcomes[0]);
			System.out.println("You rehearsal count is " + rehearsalCount);         
			if (dieOutcomes[0] + rehearsalCount >= budget) {            
				System.out.println("You have succeeded!");
            int shots = scene.getShotCount();
				if (shots > 0) {
					System.out.println("There are " + (shots-1) + " shots left.");
					if(currentRole.getOnCard()) {
						System.out.println("You recieved 2 credits");
						credits = credits + 2; 
					} else {
						System.out.println("You recieved 1 credit and 1 dollar");
						credits++; 
						money++; 
					}
               shots = scene.decrementShotCount();
					retvalue = 1; 
				}if(shots == 0) {
					System.out.println("You have completed the scene!");
					acting = false; 
					int[] diceOutcomes = rollDice(scene.getBudget()); 
					scene.distributeBonuses(diceOutcomes); 
					retvalue = 2; 
				}
			} else {
				System.out.println("You have failed."); 
				if(!currentRole.getOnCard()) {
					money++; 
				}
				retvalue = 1; 
			}
		} else {
			System.out.println("You cannot act because you haven't taken up a role yet.");
			retvalue = 0; 
		}
      return retvalue;
	}
	
   /* Pre: Accepts either 'money' or 'credits,' and the rank the player wants to upgrade to.
   *  Post: Returns the value of upgradeRank (from UtilityRoom), or false if it did not enter there.
   */
	public boolean upgrade(String type, int level) {
		if(position.getName().equals("Casting Office")) {
			UtilityRoom util = (UtilityRoom) position;
			if(level > 1) {            				
            return util.upgradeRank(type, this, level);
			} else {        
				util.displayAvailableRanks(rank, credits, money); 
				return false; 
			}
		} else {
			System.out.println("You need to be in the casting office to upgrade your level");
			return false; 
		}
	}

   /* Pre: Accepts a string from System.in
   *  Post: Returns that string sent to all lower case.
   */
	private String getInput(){
		Scanner scanInput = new Scanner(System.in);
		String input = scanInput.nextLine();
		scanInput.close();
		return input.toLowerCase();
	}  

   /* Pre: A number of dice to roll.
   *  Post: Returns an array of the different values rolled by each die.
   */
	public int[] rollDice(int numDice){
		Random d6 = new Random();
		int[] values = new int[numDice];
		for(int i = 0; i < numDice; i++){
			values[i] = d6.nextInt(6) + 1;         
		}
		return values;
	}
}
