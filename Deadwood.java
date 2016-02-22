package deadwood1;

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
    	Scanner input = new Scanner(System.in);
    	boolean okInput = false; 
    	while (!okInput){
            try{
                numPlayers = input.nextInt();
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
    	
    	//initialize
    	players = initPlayers(numPlayers, utilityRooms.get("trailers")); 
    	
    	//game loop 
    	while(daysLeft != 0) {
    		
    		//set cards for each room 
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
    		
    		//
    		while (daysLeft != 0) {
    			int scenesShot = 0; 
    			while (scenesShot < 9) {
    				int playerNum = 0; 
    				for(Player player : players) {
    					playerNum++; 
    					System.out.println("Player" + playerNum + " it is your turn");
    					
    				}
    			}
    		}
    		
    		
    	}
    	
//        while (numDays != 0){
//            int scenesShot = 0;
//            //Initialize cards for each day
//            int numRooms = rooms.length;
//            Random cardPicker = new Random();
//            for(int i = 0; i < numRooms; i++){
//               int cardChosen = cardPicker.nextInt(40);
//               while(cardArray[cardChosen].getUsed()){
//                  cardChosen = (cardChosen + 1) % 40;
//               }
//               
//               rooms[i].setCard(cardArray[cardChosen]);
//               cardArray[cardChosen].setUsed();
//            }
//            while (scenesShot < 9){
//                  Scanner inRead = new Scanner(System.in);
//                  for (int i = 0; i < players.length; i++){
//                        System.out.println("Player " + i + " it is your turn.");
//                        if(players[i].getPosition().getName().equals("Casting Office")){
//                           System.out.println("You may upgrade your rank before you move.");
//                           int newRank = inRead.nextInt();                          
//                           players[i].upgrRank(newRank);
//                        }
//                        if (!players[i].isActing())
//                        {
//                            players[i].move();
//                        }else{
//                           System.out.println("You may act or rehearse your role.");
//                           okInput = false;
//                           while(!okInput){
//                              String input = inRead.nextLine();
//                              if(input.equals("act")){
//                                 okInput = true;
//                                 players[i].act();
//                              }else if(input.equals("rehearse")){
//                                 okInput = true;
//                                 players[i].rehearse();
//                              }else if(input.equals("quit")){
//                                 okInput = true;                              
//                              }else{
//                                 System.out.println("That was incorrect input, type 'act' or 'rehearse' to continue, or 'quit' to stop.");
//                              }
//                           }
//                        }
//                  }
//            }
//            numDays--;
//        }
    }
    
    
    private static void initGame() throws FileNotFoundException {
    	cardArray = initCards(); 
    	sceneRooms = initScenes();    	
    	utilityRooms = initUtilities(); 
	}
    
    /*returns a map of scene objects */
    private static Map<String, SceneRoom> initScenes() {
    	Map<String, SceneRoom> sceneRooms = new HashMap<String, SceneRoom>(); 
    	
    	sceneRooms.put("main-street", new SceneRoom("Main Street", "Scene", 3, new Role("Railroad Worker", 1, "I'm a steel-drivin' man!"), new Role("Falls off Roof", 2, "Aaaaaaaaiiigggghh!"), new Role("Woman in Black Dress", 2, "Well, I'll be!"), new Role("Mayor McGinty", 4, "People of Deadwood!")));
    	sceneRooms.put("saloon", new SceneRoom("Saloon", "Scene", 2, new Role("Woman in Red Dress", 2, "Come up and see me!"), new Role("Reluctant Farmer", 1, "I ain't so sure about that!"), new Role(null, 0, null), new Role(null, 0, null)));
    	sceneRooms.put("ranch", new SceneRoom("Ranch", "Scene", 2, new Role("Shot in Leg", 1, "Ow! Me Leg!"), new Role("Man Under Horse", 3, "A little help here!"), new Role("Saucy Fred", 2, "That's what she said!"), new Role(null, 0, null)));
    	sceneRooms.put("secret-hideout", new SceneRoom("Secret Hideout", "Scene", 3, new Role("Clumsy Pit Fighter", 1, "Hit me!"), new Role("Thug with Knife", 2, "Meet Suzy, my murderin' knife."), new Role("Dangerous Tom", 3, "There's two ways we can do this"), new Role("Penny, who is Lost", 4, "Oh, woe! For I am lost!")));
    	sceneRooms.put("bank", new SceneRoom("Bank", "Scene", 1, new Role("Flustered Teller", 3, "Would you like a large bill, sir?"), new Role("Suspicious Gentleman", 2, "Can you be more specific?"), new Role(null, 0, null), new Role(null, 0, null))); 
    	sceneRooms.put("hotel", new SceneRoom("Hotel", "Scene", 3, new Role("Faro Player", 1, "Hit me!"), new Role("Sleeping Drunkard", 1, "Zzzzz... Whiskey!"), new Role("Australian Bartender", 3, "What'll it be, mate?"), new Role("Falls from Balcony", 2, "Arrrgghh!!")));
    	sceneRooms.put("church", new SceneRoom("Church", "Scene", 2, new Role("Dead Man", 1, "..."), new Role("Crying Woman", 2, "Oh, the humanity!"), new Role(null, 0, null), new Role(null, 0, null)));
    	sceneRooms.put("train-station", new SceneRoom("Train Station", "Scene", 3, new Role("Dragged by Train", 1, "Omgeezers!"), new Role("Crusty Propector", 1, "Aww, peaches!"), new Role("Cyrus the Gunfighter", 4, "Git to fightin' or git away"), new Role("Preacher with Bag", 2, "The Lord will provide.")));
    	sceneRooms.put("jail", new SceneRoom("Jail", "Scene", 1, new Role("Prisoner in Cell", 2, "Zzzzz... Whiskey!"), new Role("Feller in Irons", 3, "Ah kilt the wrong man!"), new Role(null, 0, null), new Role(null, 0, null)));
    	sceneRooms.put("general-store", new SceneRoom("General Store", "Scene", 2, new Role("Man in Overalls", 1, "Looks like a storm's comin' in."), new Role("Mister Keach", 3, "Howdy, stranger."), new Role(null, 0, null), new Role(null, 0, null)));
    	
    	sceneRooms.get("main-street").setAdjacentRooms(new String[]{"trailers", "saloon", "jail"}); 
    	sceneRooms.get("saloon").setAdjacentRooms(new String[]{"main-street", "trailers", "general-store", "bank"});
    	sceneRooms.get("ranch").setAdjacentRooms(new String[]{"general-store", "jail", "saloon", "train-station"});
    	sceneRooms.get("secret-hideout").setAdjacentRooms(new String[]{"casting-office", "ranch", "church"});
    	sceneRooms.get("bank").setAdjacentRooms(new String[]{"hotel", "church", "saloon", "ranch"});
    	sceneRooms.get("hotel").setAdjacentRooms(new String[]{"trailers", "bank", "church"});
    	sceneRooms.get("church").setAdjacentRooms(new String[]{"hotel","bank", "secret-hideout"});
    	sceneRooms.get("train-station").setAdjacentRooms(new String[]{"jail", "general-store", "casting-office"});
    	sceneRooms.get("jail").setAdjacentRooms(new String[]{"main-street", "general-store", "train-station"});
    	sceneRooms.get("general-store").setAdjacentRooms(new String[]{"jail", "saloon", "ranch", "train-station"});
    	
    	return sceneRooms; 
    }
    
    /*returns a map of utilityRoom objects*/
    private static Map<String, UtilityRoom> initUtilities(){
    	Map<String, UtilityRoom> utilityRooms = new HashMap<String, UtilityRoom>(); 
    	
    	utilityRooms.put("trailers", new UtilityRoom("trailers"));
    	utilityRooms.put("casting-office", new UtilityRoom("casting-office"));
    	
    	utilityRooms.get("trailers").setAdjacentRooms(new String[]{"main-street", "saloon", "hotel"});
    	utilityRooms.get("casting-office").setAdjacentRooms(new String[]{"train-station", "ranch", "secret-hideout"});
   
    	return utilityRooms; 
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
            Role roleOne = new Role(nameOne, levelOne, phraseOne);
            Role roleTwo = new Role(nameTwo, levelTwo, phraseTwo);
            Role roleThree = new Role(nameThree, levelThree, phraseThree);
            cardArray[i] = new Card(budget, roleOne, roleTwo, roleThree, title, description);
            i++;
        }
        return cardArray;
    }
	
	private static Player[] initPlayers(int numPlayers, UtilityRoom trailer) {
		Player[] players = new Player[numPlayers];
		
		for (int i = 0; i < numPlayers; i++) {
			players[i] = new Player(trailer); 
		}
		
		return players; 
	}
   
//    private static Player[] initializePlayers (int numPlayers, UtilityRoom trailers){
//        Player p1;
//        Player p2;
//        Player p3;
//        Player p4;
//        Player p5;
//        Player p6;
//        Player p7;
//        Player p8;
//
	
	
	
//        switch (numPlayers){
//            case 2:
//            numDays = 3;
//            p1 = new Player(trailers);
//            p2 = new Player(trailers);
//            return new Player[] {p1, p2};
//            case 3:
//            numDays = 3;
//            p1 = new Player(trailers);
//            p2 = new Player(trailers);
//            p3 = new Player(trailers);
//            return new Player[] {p1, p2, p3};
//            case 4:
//            p1 = new Player(trailers);
//            p2 = new Player(trailers);
//            p3 = new Player(trailers);
//            p4 = new Player(trailers);
//            return new Player[] {p1, p2, p3, p4};
//            case 5:
//            p1 = new Player(2, 1, trailers);
//            p2 = new Player(2, 1, trailers);
//            p3 = new Player(2, 1, trailers);
//            p4 = new Player(2, 1, trailers);
//            p5 = new Player(2, 1, trailers);
//            return new Player[] {p1, p2, p3, p4, p5};
//            case 6:
//            p1 = new Player(4, 1, trailers);
//            p2 = new Player(4, 1, trailers);
//            p3 = new Player(4, 1, trailers);
//            p4 = new Player(4, 1, trailers);
//            p5 = new Player(4, 1, trailers);
//            p6 = new Player(4, 1, trailers);
//            return new Player[] {p1, p2, p3, p4, p5, p6};
//            case 7:
//            p1 = new Player(0, 2, trailers);
//            p2 = new Player(0, 2, trailers);
//            p3 = new Player(0, 2, trailers);
//            p4 = new Player(0, 2, trailers);
//            p5 = new Player(0, 2, trailers);
//            p6 = new Player(0, 2, trailers);
//            p7 = new Player(0, 2, trailers);
//            return new Player[] {p1, p2, p3, p4, p5, p6, p7};
//            case 8:
//            p1 = new Player(0, 2, trailers);
//            p2 = new Player(0, 2, trailers);
//            p3 = new Player(0, 2, trailers);
//            p4 = new Player(0, 2, trailers);
//            p5 = new Player(0, 2, trailers);
//            p6 = new Player(0, 2, trailers);
//            p7 = new Player(0, 2, trailers);
//            p8 = new Player(0, 2, trailers);
//            return new Player[] {p1, p2, p3, p4, p5, p6, p7, p8};
//       }
//       return null;
//    }
}
