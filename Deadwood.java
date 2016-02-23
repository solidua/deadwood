//package deadwood1;

import java.util.*;
import java.util.Map.Entry;
import java.lang.*;
import java.io.*;
class Deadwood{
	
	static Map<String, SceneRoom> sceneRooms;
	static Map<String, UtilityRoom> utilityRooms; 
	static Card[] cardArray; 
	static Player[] players; 
	static int numPlayers;
	static int daysLeft = 4; 
	
	public static void main(String[] args) throws FileNotFoundException{      
    	initGame(); 
    	System.out.println("Welcome to DeadWood");
    	
    	//prompts user for number of players 
    	System.out.print("Please enter the number of player (2 - 8):");
    	Scanner in = new Scanner(System.in);
    	boolean okInput = false; 
    	while (!okInput){
            try{
                numPlayers = in.nextInt();
                if (numPlayers >= 2 && numPlayers <= 8){
                   okInput = true;
                }else{
                   System.out.print("Please enter a value between 2 and 8: ");
                }
            }catch (InputMismatchException e){
                System.out.println();
                System.out.println("Incorrect Input");
                System.exit(1);
            }
         }
    	in.nextLine(); 
    	
    	//initialize
    	players = initPlayers(numPlayers, utilityRooms.get("trailers")); 
    	
    	//game loop 
    	while (daysLeft != 0) {
    		System.out.println("There are " + daysLeft + " days left.");
    		
    		cardPicker(); 
    		for(Player player : players) {
    			player.setPosition(utilityRooms.get("trailers"));
    		}
    		
    		int scenesShot = 0; 
    		while (scenesShot < 9) {
    			int playerNum = 0; 
    			for(Player player : players) {
    				playerNum++; 
    				System.out.println("Player" + playerNum + " it is your turn");
    				System.out.print("> ");

    				String input = in.nextLine(); 
    				boolean turnOver = false; 
    				boolean moved = false; 
    				while(!turnOver) {
    					String[] inputArray = input.split("\\s+");
    					switch(inputArray[0]) {
    					case "who":
    						player.who(); 
    						break; 
    					case "where":
    						player.where(); 
    						break; 
    					case "move": 
    						if (inputArray.length == 1) {
    							player.move("none"); 
    						} else if (!moved) {
    							String roomName = mergeString(inputArray);
    							moved = player.move(roomName.toLowerCase());
    						} else {
    							System.out.println("You have already moved, you cannot move again.");
    						}
    						break; 
    					case "work": 
    						if (inputArray.length == 1) {
    							player.work("none"); 
    						} else {
    							String roleName = mergeString(inputArray); 
    							turnOver = player.work(roleName.toLowerCase());
    						}
    						break; 
    					case "upgrade":
                     //turnOver = player.upgrade();
    						break; 
    					case "rehearse": 
    						if(!moved) {
    							turnOver = player.rehearse(); 
    						} else {
    							System.out.println("You may not rehearse because you just moved.");
    						}
    						break; 
    					case "act": 
    						if(!moved) {
    							int res = player.act();
    							if(res == 0) {
    								// no role yet, do nothing 
    							} else if (res == 1) {
    								turnOver = true; 
    							} else if (res == 2) {
    								scenesShot++; 
    								turnOver = true; 
    							}
    						} else {
    							System.out.println("You may not act because you just moved.");
    						}
    						break; 
    					case "end": 
    						turnOver = true; 
    						break; 
    					}

    					if(!turnOver) {
    						System.out.print("> ");
    						input = in.nextLine(); 
    					}
    				}
    			}
    		}
    		daysLeft--; 
    	}

    	//game over
    	System.out.println("The game has ended. The scores are");
    	calculateScores(); 
    }
	
	private static void cardPicker() {
		Random cardPicker = new Random(); 
		for(Entry<String, SceneRoom> entry : sceneRooms.entrySet()) {
			SceneRoom scene = entry.getValue(); 
			int cardChosen = cardPicker.nextInt(40); 
			while(cardArray[cardChosen].getUsed()) {
				cardChosen = (cardChosen + 1) % 40; 
			}
			
			scene.setCard(cardArray[cardChosen]); 
			cardArray[cardChosen].setUsed(true);
		}
	}
	
	private static void calculateScores() {
		int score = 0; 
		int maxScore = 0;
		String maxPlayer = "";
		for(Player player : players) {
			score = player.getMoney() + player.getCredits(); 
			score = score + (5 * player.getRank()); 
			System.out.println(player.getName() + " scored " + score + " points." );
			if (score > maxScore) {
				maxScore = score; 
				maxPlayer = player.getName(); 
			}
		}
		
		System.out.println("The winner is " + maxPlayer);
	}
	
	private static String mergeString(String[] strArray){
		String str = "";
		for(int i = 1; i < strArray.length; i++) {
			str = str + " " + strArray[i]; 
		}
		return str.substring(1); 	
	}
    
	/*sets up the board*/
    private static void initGame() throws FileNotFoundException {
    	cardArray = initCards();
    	initScenes();
	}
    
    /*returns a map of scene objects */
    private static void initScenes() {
    	sceneRooms = new HashMap<String, SceneRoom>(); 
    	utilityRooms = new HashMap<String, UtilityRoom>(); 
    	
    	//create scene and utility rooms
    	SceneRoom mainStreet = new SceneRoom("Main Street", "Scene", 3, new Role("Railroad Worker", 1, "I'm a steel-drivin' man!", false), new Role("Falls off Roof", 2, "Aaaaaaaaiiigggghh!", false), new Role("Woman in Black Dress", 2, "Well, I'll be!", false), new Role("Mayor McGinty", 4, "People of Deadwood!", false));
    	SceneRoom saloon = new SceneRoom("Saloon", "Scene", 2, new Role("Woman in Red Dress", 2, "Come up and see me!", false), new Role("Reluctant Farmer", 1, "I ain't so sure about that!", false));
    	SceneRoom ranch = new SceneRoom("Ranch", "Scene", 2, new Role("Shot in Leg", 1, "Ow! Me Leg!", false), new Role("Man Under Horse", 3, "A little help here!", false), new Role("Saucy Fred", 2, "That's what she said!", false));
    	SceneRoom secretHideout = new SceneRoom("Secret Hideout", "Scene", 3, new Role("Clumsy Pit Fighter", 1, "Hit me!", false), new Role("Thug with Knife", 2, "Meet Suzy, my murderin' knife.", false), new Role("Dangerous Tom", 3, "There's two ways we can do this", false), new Role("Penny, who is Lost", 4, "Oh, woe! For I am lost!", false));
    	SceneRoom bank = new SceneRoom("Bank", "Scene", 1, new Role("Flustered Teller", 3, "Would you like a large bill, sir?", false), new Role("Suspicious Gentleman", 2, "Can you be more specific?", false)); 
    	SceneRoom hotel = new SceneRoom("Hotel", "Scene", 3, new Role("Faro Player", 1, "Hit me!", false), new Role("Sleeping Drunkard", 1, "Zzzzz... Whiskey!", false), new Role("Australian Bartender", 3, "What'll it be, mate?", false), new Role("Falls from Balcony", 2, "Arrrgghh!!", false));
    	SceneRoom church = new SceneRoom("Church", "Scene", 2, new Role("Dead Man", 1, "...", false), new Role("Crying Woman", 2, "Oh, the humanity!", false));
    	SceneRoom trainStation = new SceneRoom("Train Station", "Scene", 3, new Role("Dragged by Train", 1, "Omgeezers!", false), new Role("Crusty Propector", 1, "Aww, peaches!", false), new Role("Cyrus the Gunfighter", 4, "Git to fightin' or git away", false), new Role("Preacher with Bag", 2, "The Lord will provide.", false));
    	SceneRoom jail = new SceneRoom("Jail", "Scene", 1, new Role("Prisoner in Cell", 2, "Zzzzz... Whiskey!", false), new Role("Feller in Irons", 3, "Ah kilt the wrong man!", false));
    	SceneRoom generalStore = new SceneRoom("General Store", "Scene", 2, new Role("Man in Overalls", 1, "Looks like a storm's comin' in.", false), new Role("Mister Keach", 3, "Howdy, stranger.", false)); 
    	
    	UtilityRoom trailers = new UtilityRoom("trailers", "Trailers", "Utility");
    	UtilityRoom castingOffice = new UtilityRoom("casting-office", "Casting Office", "Utility");
    	
    	//place rooms into designated maps
    	sceneRooms.put("main-street", mainStreet); 
    	sceneRooms.put("saloon", saloon); 
    	sceneRooms.put("ranch", ranch); 
    	sceneRooms.put("secret-hideout", secretHideout); 
    	sceneRooms.put("bank", bank); 
    	sceneRooms.put("hotel", hotel); 
    	sceneRooms.put("church", church); 
    	sceneRooms.put("train-station", trainStation); 
    	sceneRooms.put("jail", jail); 
    	sceneRooms.put("general-store", generalStore);  
    	
    	utilityRooms.put("trailers", trailers); 
    	utilityRooms.put("casting-office", castingOffice);
    	
    	//set adjacent rooms for each room
    	sceneRooms.get("main-street").setAdjacentRooms(new Room[]{trailers, saloon, jail}); 
    	sceneRooms.get("saloon").setAdjacentRooms(new Room[]{mainStreet, trailers, generalStore, bank});
    	sceneRooms.get("ranch").setAdjacentRooms(new Room[]{generalStore, jail, saloon, trainStation});
    	sceneRooms.get("secret-hideout").setAdjacentRooms(new Room[]{castingOffice, ranch, church});
    	sceneRooms.get("bank").setAdjacentRooms(new Room[]{hotel, church, saloon, ranch});
    	sceneRooms.get("hotel").setAdjacentRooms(new Room[]{trailers, bank, church});
    	sceneRooms.get("church").setAdjacentRooms(new Room[]{hotel,bank, secretHideout});
    	sceneRooms.get("train-station").setAdjacentRooms(new Room[]{jail, generalStore, castingOffice});
    	sceneRooms.get("jail").setAdjacentRooms(new Room[]{mainStreet, generalStore, trainStation});
    	sceneRooms.get("general-store").setAdjacentRooms(new Room[]{jail, saloon, ranch, trainStation});
    	
    	utilityRooms.get("trailers").setAdjacentRooms(new Room[]{mainStreet, saloon, hotel});
    	utilityRooms.get("casting-office").setAdjacentRooms(new Room[]{trainStation, ranch, secretHideout});
    }
    
    /*returns an array of cards*/
	private static Card[] initCards () throws FileNotFoundException{
        File cardFile = new File("cards.txt");
        Scanner input = new Scanner(cardFile);
        Card[] cardArray = new Card[40];
        int i = 0;
        while (input.hasNext()){
            String cardLine = input.nextLine();
            Scanner cardReader = new Scanner(cardLine);
            cardReader.useDelimiter(", ");
            String title = cardReader.next();
            String description = cardReader.next();
            int levelOne = cardReader.nextInt();
            String nameOne = cardReader.next();
            String phraseOne = cardReader.next();
            int levelTwo = cardReader.nextInt();
            String nameTwo = cardReader.next();
            String phraseTwo = cardReader.next();
            int levelThree = cardReader.nextInt();
            String nameThree = cardReader.next();
            String phraseThree = cardReader.next();
            int budget = cardReader.nextInt();
            Role roleOne = new Role(nameOne, levelOne, phraseOne, true);
            Role roleTwo = new Role(nameTwo, levelTwo, phraseTwo, true);
            Role roleThree = new Role(nameThree, levelThree, phraseThree, true);
            cardArray[i] = new Card(budget, roleOne, roleTwo, roleThree, title, description);
            i++;
        }
        return cardArray;
    }
	
	private static Player[] initPlayers(int numPlayers, UtilityRoom trailer) {
		Player[] players = new Player[numPlayers];
		int bonusCredit = 0; 
		int bonusRank = 1;
		
		switch(numPlayers) {
		case 2:  
		case 3: 
			daysLeft = 3; 
			break; 
		case 5: 
			bonusCredit = 2; 
			break; 
		case 6: 
			bonusCredit = 4; 
			break; 
		case 7: 
		case 8:
			bonusRank = 2;
			break; 
		}
		
		
		for (int i = 0; i < numPlayers; i++) {
			players[i] = new Player(trailer, "Player-" + (i + 1)); 
			players[i].addCredit(bonusCredit); 
			players[i].setRank(bonusRank); 
		}
		
		return players; 
	}   
}
