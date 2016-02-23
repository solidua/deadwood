//package deadwood1;

import java.util.HashMap;

public class UtilityRoom extends Room{
   private int[][] upgradeTable;
	private String utilityType; 

	
	public UtilityRoom(String utilType, String name, String type) {
		roomName = name;
		roomType = type; 
		utilityType = utilType;
      //The rank upgrades rank 2 is position 0, rank 3 is position 1, etc.
      upgradeTable = new int[][]{{4, 5}, {10, 10}, {18, 15}, {28, 20}, {40, 25}};
	}
	
	public String getType() {
		return utilityType; 
	}
	
   //
   public void displayAvailableRanks(int currentRank, int credits, int money){
      boolean upgradeable = true;
      int i = 0;
      int[] currRank = {0, 0}; //{Money, Credits}
      System.out.println("These are the available ranks you can upgrade:");
      while(upgradeable){
         currRank = upgradeTable[i];
         if(money < currRank[0] && credits < currRank[1]){
            upgradeable = false;
         }else{
            System.out.println("\t Rank " + (i + 2) + ": $" + currRank[0] + " or " + currRank[1] + " cr.");
            i++; 
         }
      }
      if(i == 0){
         System.out.println("You cannot upgrade your rank! Go out there and act!");
      }
   }
   
   public boolean upgradeRank(String payType, Player player, int wantedRank){
      if(wantedRank < player.getRank() || wantedRank > 6){
         System.out.println("That is an invalid rank to take.");
         return false;
      }else{
         int[] currRank = upgradeTable[wantedRank-2];
         if(payType.equals("money")){
            if(player.getMoney() >= currRank[0]){
               player.addMoney(-currRank[0]);
               player.setRank(wantedRank);
               System.out.println("You are now rank " + wantedRank + "!");
               return true;
            }else{
               System.out.println("You are too poor to upgrade to that rank!");
               return false;
            }
         }else if(payType.equals("credits")){
            if(player.getCredits() >= currRank[1]){
               player.addCredit(-currRank[1]);
               player.setRank(wantedRank);
               System.out.println("You are now rank " + wantedRank + "!");
               return true;
            }else{
               System.out.println("You are too poor to upgrade to that rank!");
               return false;
            }
         }
      }
      return false;
   }
   
	public int improveRank(int rankWanted, String payment) {
		switch(rankWanted) {
			case 2:
            if (payment.equals("money")){
               return 4;
            }else{
               return 5;
            }
			case 3:
				if (payment.equals("money")){
               return 10;
            }else{
               return 10;
            } 
			case 4: 
				if (payment.equals("money")){
               return 18;
            }else{
               return 15;
            } 
			case 5: 
				if (payment.equals("money")){
               return 28;
            }else{
               return 20;
            } 
			case 6:
				if (payment.equals("money")){
               return 40;
            }else{
               return 25;
            } 
		}
    return 0;
	}
	
}
