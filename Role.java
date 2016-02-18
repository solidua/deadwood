class Role{
  private String name;
  private int level;
  private String phrase;
<<<<<<< HEAD
=======
  private String description;
>>>>>>> 986beb67bc4893f11263ac7c17db6e08fa1a0bdc
  private boolean taken;
  
  public Role (){
      name = null;
      level = 0;
      phrase = null;
<<<<<<< HEAD
      taken = false;
  }
  public Role (String moniker, int stat, String saying){
      name = moniker;
      level = stat;
      phrase = saying;
=======
      description = null;
      taken = false;
  }
  public Role (String moniker, int stat, String saying, String details){
      name = moniker;
      level = stat;
      phrase = saying;
      description = details;
>>>>>>> 986beb67bc4893f11263ac7c17db6e08fa1a0bdc
      taken = false;
  }
  public String getName(){
      return name;
  }
  public int getLevel(){
      return level;
  }
  public String getPhrase(){
      return phrase;
  }
<<<<<<< HEAD
=======
  public String getDescription(){
      return description;
  }
>>>>>>> 986beb67bc4893f11263ac7c17db6e08fa1a0bdc
  public boolean getTaken(){
      return taken;
  }
  public void setName(String newName){
      name = newName;
  }
  public void setLevel(int newLevel){
      level = newLevel;
  }
  public void setPhrase(String newPhrase){
      phrase = newPhrase;
  }
<<<<<<< HEAD
=======
  public void setDescription(String newDescription){
      description = newDescription;
  }
>>>>>>> 986beb67bc4893f11263ac7c17db6e08fa1a0bdc
  public void setTaken(boolean newTaken){
      taken = newTaken;
  }
}
