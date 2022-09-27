import java.io.*;
import java.sql.SQLException;


public class Quest
{
  
  
  RunEvent1 RE1 = new RunEvent1();
  Beginning B = new Beginning();
  CsvSize C = new CsvSize();
  Menu M = new Menu();
  AuthorsNotes A = new AuthorsNotes();
  Town T = new Town();
  Level L = new Level();
  Dice R = new Dice();
  Leaderboard Lead = new Leaderboard();
  

  private Event1_Data Encounter[];
  private Player_Data PlayerInfo;
  private Weapon_Data Weapon[];
  private Badge_Data Badge[];
  

  public void processQuest()  throws IOException {

    

    aquireEvents();
    aquirePlayerInfo();
    aquireWeapon();
    aquireBadge();
    
    int choice = M.processMenu();

    if(choice == 1){
      RunGame();
    }
    else if(choice == 2){
      try{      
        Lead.processLeaderboard();
      }
      catch(SQLException e){

      }
    }
    else if(choice == 3){
      A.processAuthorsNotes();
    }

    
    

  }

  public void RunGame(){

    B.processBeginning(PlayerInfo, Weapon);

   


    int event1 = 0; //This is for regular day to day events
    int boss = 0; //This is to allow boss fights to only happen occasionally
    int turns = 0;  //3 of these in a normal day
    int rotations = 0;  //amount of days that have happened
    int stage = 1;  //used to unlock events
    int stageup = 1;  //Used to tell if town has played the new event

    while(PlayerInfo.getHealth() > 0){

      while(PlayerInfo.getLevel() * PlayerInfo.getLevel() * 10 <= PlayerInfo.getXp()){
        PlayerInfo = L.processLevel(PlayerInfo, Badge);
      }

      //this is where staging up will happen
      if ((PlayerInfo.getLevel() > 3) && (turns == 3) && (stage < 2)){
        stage = 2;
        stageup = 2;
        event1 = 1;
      }
      if ((PlayerInfo.getLevel() > 4) && (turns == 3) && (stage < 3)){
        stage = 3;
        stageup = 3;
        event1 = 2;
      }

      if ((PlayerInfo.getLevel() >= 8) && (stage < 4)){
        
        stage = 4;
        stageup = 4;
        boss = 1;
      
      }

      
      if (turns == 3){
        rotations++;
        T.processTown(PlayerInfo, stageup, rotations);
        turns = 0;
        stageup = 0;
      }

      // System.out.println(event); //This is for testing

      

      int CurrentEvent = GetEvent(event1);

      


      
      RE1.processRunEvent1(Encounter, CurrentEvent, PlayerInfo);
      
      turns++;

      //This is the boss fight
      if((stage == 4) && (stageup == 0)){
        
        RE1.processRunEvent1(Encounter, 3, PlayerInfo);


        turns = 3;
        boss = 0;
        stageup = 1;
      }
      // System.out.println(turns);  /* Used to debug */
      


    }

    


  }
  
  //This reads in Encounter
  public void aquireEvents(){

    Event1Initiate E1 = new Event1Initiate();
    
    Encounter = E1.processEvent1Initiate("Event/Event1.csv");


  }

  //This reads in PlayerInfo
  public void aquirePlayerInfo(){

    PlayerInitiate P = new PlayerInitiate();

    PlayerInfo = P.processPlayerInitiate();

  }

  //This reads in Weapon
  public void aquireWeapon(){

    WeaponInitiate W = new WeaponInitiate();
    
    Weapon = W.processWeaponInitiate("Player/Weapon/Weapon.csv");

  }

  //This reads in Badge
  public void aquireBadge(){

    BadgeInitiate B = new BadgeInitiate();
    
    Badge = B.processBadgeInitiate("Player/Badge/Badge.csv");

  }

  //This chooses what event will happen
  public int GetEvent(int event){

    int CurrentEvent = R.rDice(event + 1, 1);

    if((event == 2) && (CurrentEvent == 0)){
      CurrentEvent = R.rDice(event + 1, 1);
    }

    return CurrentEvent;

  }

}
