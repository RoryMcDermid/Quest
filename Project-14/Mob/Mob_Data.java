
public class Mob_Data
{
  
  private String Name;
  private double Attack;
  private double Health;
  private double Armour;
  private double Speed;
  private double Gold;
  private double Xp;
  private String Id;
  

  public Mob_Data() {

    Name = "";
    Attack = 0;
    Health = 0;
    Armour = 0;
    Speed = 0;
    Gold = 0;
    Xp = 0;
    Id = "";

  

  }

  

  public String getName(){
    return Name;
  }

  public void setName(String in){
    Name = in;
  }

  public double getAttack(){
    return Attack;
  }

  public void setAttack(double in){
    Attack = in;
  }

  public double getHealth(){
    return Health;
  }

  public void setHealth(double in){
    Health = in;
  }

  public double getArmour(){
    return Armour;
  }

  public void setArmour(double in){
    Armour = in;
  }

  public double getSpeed(){
    return Speed;
  }

  public void setSpeed(double in){
    Speed = in;
  }

  public double getGold(){
    return Gold;
  }

  public void setGold(double in){
    Gold = in;
  }

  public double getXp(){
    return Xp;
  }

  public void setXp(double in){
    Xp = in;
  }

  public String getId(){
    return Id;
  }

  public void setId(String in){
    Id = in;
  }



}