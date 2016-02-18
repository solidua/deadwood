class Card{
    private int budget;
    private String title;
    private String description;
    private Role roleOne;
    private Role roleTwo;
    private Role roleThree;
    private boolean beenUsed;

    public Card (){
        budget = 0;
        title = null;
        description = null;
        roleOne = null;
        roleTwo = null;
        roleThree = null;
        beenUsed = false;
    }
    public Card (int money, Role firstRole, Role secondRole, Role thirdRole, String name, String explanation){
        budget = money;
        roleOne = firstRole;
        roleTwo = secondRole;
        roleThree = thirdRole;
        title = name;
        description = explanation;
        beenUsed = false;
    }
    public int getBudget(){
        return budget;
    }
    public Role[] getRoles(){
        Role[] roleArray ={roleOne, roleTwo, roleThree};
        return roleArray;
    }
    public String getTitle(){
        return  title;
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
    public void roleTaken(Role takenRole){
        takenRole.setTaken(true);
    }
}