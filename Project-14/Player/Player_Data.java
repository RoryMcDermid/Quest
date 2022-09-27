import java.io.*;

public class Player_Data
{
  
  CsvSize C = new CsvSize();
  private String Name;
  private int Health;
  private int Max_Health;
  private int Armour;
  private int Speed;
  private Weapon_Data Weapon;
  private int Xp;
  private int Gold;
  private int Level;
  private int PeopleKnown[];
  private int Kills[][][][];
  private Role_Data Role;
  private int Badge[];
  private int difficulty;
  

  public Player_Data() {

    Name = "";
    Health = 0;
    Max_Health = 0;
    Armour = 0;
    Speed = 0;
    Weapon = new Weapon_Data();
    Xp = 0;
    Gold = 0;
    Level = 0;
    PeopleKnown = new int[5];
    try {
      Kills = new int[C.processCsvSize("Mob/Csvs/prefix.csv")][C.processCsvSize("Mob/Csvs/type.csv")][C.processCsvSize("Mob/Csvs/base.csv")][C.processCsvSize("Mob/Csvs/suffix.csv")];
      Badge = new int[C.processCsvSize("Player/Badge/Badge.csv")];       
    }
    catch(IOException e) {
      e.printStackTrace();
    }
    Role = new Role_Data();
    difficulty = 0;

  

  }

  

  public String getName(){
    return Name;
  }

  public void setName(String in){
    Name = in;
  }

  
  public int getHealth(){
    return Health;
  }

  public void setHealth(int in){
    Health = in;
  }

  
  public int getMax_Health(){
    return Max_Health;
  }

  public void setMax_Health(int in){
    Max_Health = in;
  }

  
  public int getArmour(){
    return Armour;
  }

  public void setArmour(int in){
    Armour = in;
  }

  
  public int getSpeed(){
    return Speed;
  }

  public void setSpeed(int in){
    Speed = in;
  }

  
  public Weapon_Data getWeapon(){
    return Weapon;
  }

  public void setWeapon(Weapon_Data in){
    Weapon = in;
  }

  
  public int getXp(){
    return Xp;
  }

  public void setXp(int in){
    Xp = in;
  }

  
  public int getGold(){
    return Gold;
  }

  public void setGold(int in){
    Gold = in;
  }

  
  public int getLevel(){
    return Level;
  }

  public void setLevel(int in){
    Level = in;
  }

  
  public int getPeopleKnown(int position){
    return PeopleKnown[position];
  }

  public void setPeopleKnown(int in, int position){
    PeopleKnown[position] = in;
  }

  
  public int getKills(int prefix, int type, int base, int suffix){
    return Kills[prefix][type][base][suffix];
  }

  public void setKills(int in, int prefix, int type, int base, int suffix){
    Kills[prefix][type][base][suffix] = in;
  }

  public int getPrefixLength(){
    return Kills.length;
  }

  public int getTypeLength(){
    return Kills[0].length;
  }

  public int getBaseLength(){
    return Kills[0][0].length;
  }

  public int getSuffixLength(){
    return Kills[0][0][0].length;
  }


  public Role_Data getRole(){
    return Role;
  }

  public void setRole(Role_Data in){
    Role = in;
  }

  public int getBadge(int position){
    return Badge[position];
  }

  public void setBadge(int in, int position){
    Badge[position] = in;
  }

  public int getDifficulty(){
    return difficulty;
  }

  public void setDifficulty(int in){
    difficulty = in;
  }



}