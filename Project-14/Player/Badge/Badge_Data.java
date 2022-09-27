
//This will only be used in stat changes, while a seperate array will handle if these have been gained or not

public class Badge_Data
{
  
  private String Badge_Name;
  private int Badge_Stat_Increase[];
  private boolean Badge_Acquired;
  
  
  

  public Badge_Data() {

    Badge_Name = "";

    //0 is health, 1 is armour, 2 is speed
    Badge_Stat_Increase = new int[3];

    Badge_Acquired = false;
    

  }

  

  public String getBadge_Name(){
    return Badge_Name;
  }

  public void setBadge_Name(String in){
    Badge_Name = in;
  }

  public int getBadge_Stat_Increase(int D1){
    return Badge_Stat_Increase[D1];
  }

  public void setBadge_Stat_Increase(int in, int D1){
    Badge_Stat_Increase[D1] = in;
  }

  public boolean getBadge_Acquired(){
    return Badge_Acquired;
  }

  public void setBadge_Acquired(boolean in){
    Badge_Acquired = in;
  }

}