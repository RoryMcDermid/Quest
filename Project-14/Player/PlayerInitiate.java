import java.io.*;

public class PlayerInitiate
{

  CsvSize C = new CsvSize();
  


  private Player_Data Player;



  public Player_Data processPlayerInitiate(){

    
    
    
    
    Player = new Player_Data();
    readPlayer_Data();

    return Player;
  }

  //This is to initialise all of the player's base stats
  public void readPlayer_Data(){

    
          
      //This is the name that the player is refered to as
      Player.setName("");

      //This is the player's current health, if it reaches 0, they die
      Player.setHealth(100);

      //This is the highest that the player's health can be
      Player.setMax_Health(100);

      //This is the player's armour value
      Player.setArmour(0);

      //This effects if the player goes before or after the mob
      Player.setSpeed(3);

      //This is the Id of the weapon that the player is wielding
      Player.setWeapon(new Weapon_Data());

      //This is how close the player is to leveling up
      Player.setXp(0);

      //This is the player's money
      Player.setGold(0);

      //This is the player's level
      Player.setLevel(1);

      

      //This is how well the player knows each NPC
      for (int i = 0; i < 5; i++){
        Player.setPeopleKnown(0, i);
      }

      //This is the amount of times that the player has killed each type of mob
      try{
        for(int p = 0; p < C.processCsvSize("Mob/Csvs/prefix.csv"); p++){
          for(int t = 0; t < C.processCsvSize("Mob/Csvs/type.csv"); t++){
            for(int b = 0; b < C.processCsvSize("Mob/Csvs/base.csv"); b++){
              for(int s = 0; s < C.processCsvSize("Mob/Csvs/suffix.csv"); s++){
                Player.setKills(0, p, t, b, s);
              }
            }
          }
        }
      }
      catch(IOException e) {
      e.printStackTrace();
      }

      //This is the player's class (tank, rogue, etc)
      Player.setRole(new Role_Data());

      try{
        for(int badge = 0; badge < C.processCsvSize("Player/Badge/Badge.csv"); badge++){
          Player.setBadge(0, badge);
        }
      }
      catch(IOException e) {
      e.printStackTrace();
      }
      Player.setDifficulty(1);
      
      

  }

}
