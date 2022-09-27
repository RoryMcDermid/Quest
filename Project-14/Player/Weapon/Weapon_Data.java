
public class Weapon_Data
{
  
  private String Weapon_Name;
  private int Weapon_Attack;
  private String Weapon_Id;
  
  

  public Weapon_Data() {

    Weapon_Name = "";
    Weapon_Attack = 0;
    Weapon_Id = "";
    

  

  }

  

  public String getWeapon_Name(){
    return Weapon_Name;
  }

  public void setWeapon_Name(String in){
    Weapon_Name = in;
  }

 
  public int getWeapon_Attack(){
    return Weapon_Attack;
  }

  public void setWeapon_Attack(int in){
    Weapon_Attack = in;
  }

 
  public String getWeapon_Id(){
    return Weapon_Id;
  }

  public void setWeapon_Id(String in){
    Weapon_Id = in;
  }


}