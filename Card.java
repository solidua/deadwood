class Card{
    private int budget;
<<<<<<< HEAD
    private String title;
    private String description;
=======
>>>>>>> 986beb67bc4893f11263ac7c17db6e08fa1a0bdc
    private Role roleOne;
    private Role roleTwo;
    private Role roleThree;
    private boolean beenUsed;

    public Card (){
        budget = 0;
<<<<<<< HEAD
        title = null;
        description = null;
=======
>>>>>>> 986beb67bc4893f11263ac7c17db6e08fa1a0bdc
        roleOne = null;
        roleTwo = null;
        roleThree = null;
        beenUsed = false;
    }
<<<<<<< HEAD
    public Card (int money, Role firstRole, Role secondRole, Role thirdRole, String name, String explanation){
=======
    public Card (int money, Role firstRole, Role secondRole, Role thirdRole){
>>>>>>> 986beb67bc4893f11263ac7c17db6e08fa1a0bdc
        budget = money;
        roleOne = firstRole;
        roleTwo = secondRole;
        roleThree = thirdRole;
<<<<<<< HEAD
        title = name;
        description = explanation;
=======
>>>>>>> 986beb67bc4893f11263ac7c17db6e08fa1a0bdc
        beenUsed = false;
    }
    public int getBudget(){
        return budget;
    }
    public Role[] getRoles(){
        Role[] roleArray ={roleOne, roleTwo, roleThree};
        return roleArray;
    }
<<<<<<< HEAD
    public String getTitle(){
        return  title;
    }
    public String getDescription(){
        return description;
    }
=======
>>>>>>> 986beb67bc4893f11263ac7c17db6e08fa1a0bdc
    public boolean getUsed(){
        return beenUsed;
    }
    public void setUsed(boolean newUsed){
        beenUsed = newUsed;
    }
    public void roleTaken(Role takenRole){
        takenRole.setTaken(true);
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 986beb67bc4893f11263ac7c17db6e08fa1a0bdc
