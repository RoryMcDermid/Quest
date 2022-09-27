
public class Role_Data
{
  
  private String Role_Name;
  private int Role_Stat_Increase[];
  
  
  

  public Role_Data() {

    Role_Name = "";

    //0 is health, 1 is armour, 2 is speed
    Role_Stat_Increase = new int[3];
    

  }

  

  public String getRole_Name(){
    return Role_Name;
  }

  public void setRole_Name(String in){
    Role_Name = in;
  }

  public int getRole_Stat_Increase(int D1){
    return Role_Stat_Increase[D1];
  }

  public void setRole_Stat_Increase(int in, int D1){
    Role_Stat_Increase[D1] = in;
  }

}