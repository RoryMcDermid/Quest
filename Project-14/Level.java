import java.io.*;

public class Level
{

  Scroll S = new Scroll();

  public Player_Data processLevel(Player_Data PlayerInfo, Badge_Data[] Badge){

    //This gets role so that the player's stats can be raised accordingly
    Role_Data Role = PlayerInfo.getRole();

    //This line increases the player's level by 1 
    PlayerInfo.setLevel(PlayerInfo.getLevel() + 1);

    //This increases the max health
    PlayerInfo.setMax_Health(PlayerInfo.getMax_Health() + Role.getRole_Stat_Increase(0));

    //These lines increase the player's current health to it's maximum or increase it by the increase to the max health + 20, whichever is lower than the max health
    if (PlayerInfo.getMax_Health() < PlayerInfo.getHealth() + Role.getRole_Stat_Increase(0) + 20){
      PlayerInfo.setHealth(PlayerInfo.getMax_Health());
    }
    else
    {
      PlayerInfo.setHealth(PlayerInfo.getHealth() + Role.getRole_Stat_Increase(0) + 20);
    }

    //This increases the armour
    PlayerInfo.setArmour(PlayerInfo.getArmour() + Role.getRole_Stat_Increase(1));

    //This increases the speed
    PlayerInfo.setSpeed(PlayerInfo.getSpeed() + Role.getRole_Stat_Increase(2));




    //This checks if the player has any badges that they haven't been awarded the stats for yet, and if there are any, the stats are awarded

    //If the int is 0, the player doesn't have the badge, if it is 1 they have it but haven't got the stats for it yet, and if it is 2 then they have the badge and have been awarded the stats

    for(int i = 0; i < Badge.length; i++){
      if(PlayerInfo.getBadge(i) == 1){

        //These lines increase the player's current health to it's maximum or increase it by the increase to the max health + 20, whichever is lower than the max health
        if (PlayerInfo.getMax_Health() < PlayerInfo.getHealth() + Badge[i].getBadge_Stat_Increase(0) + 20){
        PlayerInfo.setHealth(PlayerInfo.getMax_Health());
        }
        else
        {
          PlayerInfo.setHealth(PlayerInfo.getHealth() + Badge[i].getBadge_Stat_Increase(0) + 20);
        }

        //This increases the armour
        PlayerInfo.setArmour(PlayerInfo.getArmour() + Badge[i].getBadge_Stat_Increase(1));

        //This increases the speed
        PlayerInfo.setSpeed(PlayerInfo.getSpeed() + Badge[i].getBadge_Stat_Increase(2));

        //This notes that the badge's stats have been awarded
        PlayerInfo.setBadge(2, i);
      }
    }

    //This says the new stats so that the player knows them
    S.processScroll("\033[31m\033[47m", "Max health = " + PlayerInfo.getMax_Health(), true);
    S.processScroll("\033[31m", "Health = " + PlayerInfo.getHealth(), true);
    S.processScroll("\033[30m", "Armour = " + PlayerInfo.getArmour(), true);
    
    S.processScroll("\033[36m", "Speed = " + PlayerInfo.getSpeed(), true);
    System.out.print("\033[39m\033[49m");


    return PlayerInfo;

  }
}