import java.util.*;
import java.lang.*;



public class Player implements Dice{
   private int rank = 1;
   private int money;
   private int credits;
   private boolean acting = false;
   private int rehearsalcount;
   private Room position;
   private boolean turnOver = false;
   
   //Constructors
   public Player(int startCredits, int startRank, Room trailers){
      rank = startRank;
      credits = startCredits;
      position = trailers;
   }
   public Player(Room trailers){
      rank = 1;
      credits = 0;
      money = 0;
      position = trailers;
   }
   
   //Getters and setters
   public int getRank(){
      return rank;
   }
   private void setRank(int newrank){
      this.rank = newrank;
   }
   public int addMoney(int pay){
      return (this.money + pay);
   }
   public int addCredit(int pay){
      return (this.credits + pay);
   }
   public boolean isActing(){
      return acting;
   }
   
   public int[] rollDice(int numDice){
      Random d6 = new Random();
      int[] values;
      values = new int[numDice];
      for(int i = 0; i < numDice; i++){
         values[i] = d6.nextInt(6) + 1;         
      }
      return values;
   }
   
   public int rehearse(){
      if(isActing()){
         turnOver = true;
         return (rehearsalcount + 1);
      }else{
         System.out.println("You can't rehearse, you aren't acting!");
         return -1; 
      }     
   }
   
   public int act(){
      if(isActing()){
         SceneRoom actingRoom = (SceneRoom)position;
         if(actingRoom.getShotCount() > 0){
            int budget = actingRoom.getBudget();
            int[] actRoll = rollDice(1);
            actRoll[1] = actRoll[1] + rehearsalcount;
            if(budget <= actRoll[1]){
               actingRoom.decrementShotCount();
            }
         }
      }
      return 1;
   }
   
   public void move(){
      if(!isActing()){
         turnOver = true;
         Room[] moveOptions = this.position.getAdjacentRooms();
         int numAdjRooms = moveOptions.length;
         boolean inputGood = false;
         int i;
         System.out.println("The adjacent rooms you can move to are:");
         for(i = 0; i < numAdjRooms; i++){
            System.out.println(moveOptions[i].getName());
         }
         String input = getInput();
         while(!inputGood){
            for(i = 0; i < numAdjRooms; i++){
               if(moveOptions[i].getName().equals(input)){
                  inputGood = true;
                  break;
               }     
            }
         }
         position = moveOptions[i];
         if(position.getRoomType().equals("Scene")){
            Role takeRole = chooseRole();
         }else if (position.getRoomType().equals("Casting Office")){
               System.out.println("You are in the Casting Office, what rank would you like to upgrade to? Enter 0 for no upgrade");
               System.out.print("2 is $4 or 5 credits, 3 is $10 or 10 credits, 4 is $18 or 15 credits, 5 is $28 dollars or 20 credits, 6 is $40 or 25 credits");
               Scanner darkly = new Scanner(System.in);
               int wantedRank = 1;
               boolean okInput = false;
               try{
                     while (!okInput){
                           wantedRank = darkly.nextInt();
                           if (wantedRank == 0 || (wantedRank >= 2 && wantedRank <= 6)){
                                 okInput = true;
                           }else{
                                 System.out.println("Wrong value given, please enter 0 for no rank upgrade or a value between 2 and 6: ");
                           }
                     }
               }catch (InputMismatchException e){
                     System.out.println("Wrong form of input, no rank upgrade will be done.");
               }
               if (wantedRank != 0){
                     rank = upgrRank(wantedRank);
               }  
         }
      }else{
         System.out.println("You can't move, you're acting!");
      }      
   }
   
   private Role chooseRole(){
      SceneRoom actingRoom = (SceneRoom)position;
      Role[] availableRoles = actingRoom.gotVisited();
      Role[] goodRoles = printRoles(availableRoles);
      System.out.println("Choose your role by typing in its name, or quit to not take a role.");
      boolean inputGood = false;
      int numGoodRoles = goodRoles.length(); 
      while(!inputGood){
         String input = getInput();
         for(int i = 0; i < numGoodRoles; i++){
            if(input.equals(goodRoles[i].getName()){
               inputGood = true;
            }
         }
      }
      Role roleTaken = goodRoles[i];
      roleTaken.setTaken(true);
      return roleTaken;
   }
   
   private Role[] printRoles(Role[] allRoles){
      System.out.println("The following roles are available to you:");
      Role[] goodRoles;
      int k = 0;
      for(int i = 0; i < 7; i++){
         if(!allRoles[i].getTaken() && (allRoles[i].getLevel() <= rank){
            System.out.println("\t", allRoles[i].getName());
            goodRoles[k] = allRoles[i];
            k++;
         }
      }
      System.out.println("The following roles are either taken or too high rank for you!");
      for(int i = 0; i < 7; i++){
         if(allRoles[i].getTaken || (allRoles[i].getLevel() > rank){
            System.out.println("\t", allRoles[i].getName());
         }
      }
      return goodRoles;
   }

   //Must be in utility room, specifically Casting Office to work
   public int upgrRank(int newrank){
      boolean inputGood = false;
      if(this.position.getRoomType().equals("Utility")){
        System.out.println("Do you want to pay for your new rank with money or credit?");
        while(!inputGood){
          String input = getInput();
          UtilityScene castingOffice = (UtilityScene)position;
          if(input.equals("money") && (this.money > castingOffice.improveRank(newrank, "money"))){
            setRank(newrank);
            inputGood = true;
          }else if(input.equals("credit") && (this.credits > castingOffice.improveRank(newrank, "credit"))){
            setRank(newrank);
            inputGood = true;
          }else if(input.equals("quit")){
            inputGood = true;
          }else if(!input.equals("credit") || !input.equals("money")){
            System.out.println("Wrong input! Please input either money or credit for your preferred method of payment!");
          }else{
            System.out.println("You don't have sufficient money/credit for that! Please choose a different payment method or type 'quit' to stop");
          }
        }
    }
    return newrank;
  }
  
   //Helper function!
   private String getInput(){
      Scanner scanInput = new Scanner(System.in);
      String input = scanInput.nextLine();
      scanInput.close();
      return input.toLowerCase();
   }  
   
   
}
