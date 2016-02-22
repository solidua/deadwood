package deadwood1;

class Card{
    private int budget;
    private String title;
    private String description;
    private Role[] cardRoles; 
    private boolean beenUsed;

    public Card (){
        budget = 0;
        title = null;
        description = null;
        beenUsed = false;
    }
    
    public Card (int money, Role firstRole, String name, String explanation){
        budget = money;
        title = name;
        description = explanation;
        beenUsed = false;
        cardRoles = new Role[]{firstRole}; 
    }
    
    public Card (int money, Role firstRole, Role secondRole, String name, String explanation){
        budget = money;
        title = name;
        description = explanation;
        beenUsed = false;
        cardRoles = new Role[]{firstRole, secondRole}; 
    }

    public Card (int money, Role firstRole, Role secondRole, Role thirdRole, String name, String explanation){
        budget = money;
        title = name;
        description = explanation;
        beenUsed = false;
        cardRoles = new Role[]{firstRole, secondRole, thirdRole}; 
    }
    
    public int getBudget(){
        return budget;
    }
    public Role[] getRoles(){
        return cardRoles; 
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public boolean getUsed(){
        return beenUsed;
    }
    public void setUsed(boolean newUsed){
        beenUsed = newUsed;
    }
    
    public void roleTaken(Role takenRole, Player player){
        takenRole.setTaken(true, player);
    }
    
    public boolean isARoleTaken() {
    	for(Role role : cardRoles) {
    		if (role.getTaken()) {
    			return true; 
    		}
    	}
    	return false; 
    }
}
