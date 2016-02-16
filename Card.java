class Card{
    private int budget;
    private Role roleOne;
    private Role roleTwo;
    private Role roleThree;
    private boolean beenUsed;

    public Card (){
        budget = 0;
        roleOne = null;
        roleTwo = null;
        roleThree = null;
        beenUsed = false;
    }
    public Card (int money, Role firstRole, Role secondRole, Role thirdRole){
        budget = money;
        roleOne = firstRole;
        roleTwo = secondRole;
        roleThree = thirdRole;
        beenUsed = false;
    }
    public int getBudget(){
        return budget;
    }
    public Role[] getRoles(){
        Role[] roleArray ={roleOne, roleTwo, roleThree};
        return roleArray;
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
