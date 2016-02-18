

public class UtilityScene extends Room{
	private String utilityType; 
	
	public UtilityScene(String type) {
		utilityType = type; 
	}
	
	public String getType() {
		return utilityType; 
	}
	
	public Boolean improveRank(int rankWanted) {
		switch(rankWanted) {
			case 2:
				break; 
			case 3:
				break; 
			case 4: 
				break; 
			case 5: 
				break; 
			case 6:
				break; 
			
		}
    return true;
	}
	
}
