package deadwood1;

class Role{
  private String name;
  private int level;
  private String phrase;
  private boolean taken;
  private boolean onCard; 
  
  public Role (){
      name = null;
      level = 0;
      phrase = null;
      taken = false;
  }
  public Role (String moniker, int stat, String saying, boolean onCard){
      name = moniker;
      level = stat;
      phrase = saying;
      taken = false;
      this.onCard = onCard; 
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
  public void setTaken(boolean newTaken){
      taken = newTaken;
  }
  
  public boolean getOnCard() {
	  return onCard; 
  }
}
