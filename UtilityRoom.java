//package deadwood1;

import java.util.HashMap;

public class UtilityRoom extends Room{

	private String utilityType; 

	
	public UtilityRoom(String utilType, String name, String type) {
		roomName = name;
		roomType = type; 
		utilityType = utilType; 	
	}
	
	public String getType() {
		return utilityType; 
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
