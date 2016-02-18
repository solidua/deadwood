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
   public Player(int startCredits, int startRank){
      rank = startRank;
      credits = startCredits;
      position = Trailers;
   }
   public Player(){
      rank = 1;
      credits = 0;
      money = 0;
      position = Trailers;
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
         //check scene is not done
         //get budget
         int budget;
         int[] actRoll = rollDice(1);
         actRoll[1] = actRoll[1] + rehearsalcount;
         if(budget <= actRoll[1]){
            position.decrementShotCount();
         }
      }
   }
   
   public Room move(){
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
         }
      }else{
         System.out.println("You can't move, you're acting!");
      }      
   }
   
   private Role chooseRole(){
      //list roles separated by ones player is high enough rank to take and those he can't take
      Role roleTaken = null;
      String input = getInput();
      return roleTaken;
   }

   //Must be in utility room, specifically Casting Office to work
   public int upgrRank(int newrank){
      boolean inputGood = false;
      if(this.position.getRoomType().equals("Utility")){
        System.out.println("Do you want to pay for your new rank with money or credit?");
        while(!inputGood){
          String input = getInput();
          if(input.equals("money") && (this.money > this.position.improveRank())){
            setRank(newrank);
            inputGood = true;
          }else if(input.equals("credit") && (this.credits > this.position.improveRank(newrank))){
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
  }
  
   //Helper function!
   private String getInput(){
      Scanner scanInput = new Scanner(System.in);
      String input = scanInput.nextLine();
      scanInput.close();
      return input.toLowerCase();
   }  
   
   
}
