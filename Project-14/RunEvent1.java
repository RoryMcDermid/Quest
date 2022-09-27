import java.util.*;
import java.io.*;


public class RunEvent1
{

  Scanner keyboard = new Scanner(System.in);
  
  MobMaker M = new MobMaker();
  Scroll S = new Scroll();
  Dice D = new Dice();
  
  
  

  private Event1_Data Encounter[];
  private Player_Data PlayerInfo;
  private Mob_Data mob;
  private int event;

  public Player_Data processRunEvent1(Event1_Data[] EvDaIn, int eventIn, Player_Data PlayerInput)   {

    Encounter = EvDaIn;
    event = eventIn;
    PlayerInfo = PlayerInput;
    mobfind();
    
    //This introduces the monster and decides if the player can escape
    String escape = InitialText();

    if(escape.equalsIgnoreCase("1")){
      S.processScroll("\033[0m", "You manage to escape from the fight", true);

    }
    else{
      if(escape.equalsIgnoreCase("2")){
        S.processScroll("\033[0m", "You decide to stand your ground and fight", true);
      }

      //This is where the combat happens
      runBattle();

      //This calculates winnings (xp, gold, etc)
      if(PlayerInfo.getHealth() > 0){
        Aftermath();
      }
      else{
        S.processScroll("\033[0m", "The " + mob.getName() + " got the better of you, you lie there, bleeding out in the sand, wondering if you could have won.", true);
        //Add a bit here to save you depending on the story?
      }
      

    }

    

    return PlayerInfo;

  }

  public String InitialText(){
    
    //This introduces the area the player is in
    S.processScroll("\033[0m", Encounter[event].getEntrance(), true);

    //This asks the player how they will approach the area
    for(int i = 0; i < 3; i++){
      S.processScroll("\033[35m", Encounter[event].getApproach(i, 0), true);
    }
    String Sinput = keyboard.next();
    int Iinput = 0;

    //There are 2 things that the player can choose to do, and one for them doing nothing,  each option has two things that happen half of the time
    int RanEvent = D.rDice(2, 1);

    //This checks if the player has inputted the correct thing (1 or 2)
    if(Sinput.equalsIgnoreCase("1") || Sinput.equalsIgnoreCase("2")){
      Iinput = Integer.parseInt(Sinput) - 1;
    }
    //This sets the option to doing nothing
    else{
      Iinput = 2;
    }
    
    //This tells the player what has happened and removes health if required
    S.processScroll("\033[0m", Encounter[event].getApproach(Iinput, 1) + mob.getName() + Encounter[event].getApproach(Iinput, 2), true);

    S.processScroll("\033[0m", Encounter[event].getApproach_Options(Iinput, RanEvent, 0), true);

    if(Integer.parseInt(Encounter[event].getApproach_Options(Iinput, RanEvent, 1)) > 0){
      S.processScroll("\033[0m", "You lose " + Encounter[event].getApproach_Options(Iinput, RanEvent, 1) + " health", true);
      PlayerInfo.setHealth(PlayerInfo.getHealth() - Integer.parseInt(Encounter[event].getApproach_Options(Iinput, RanEvent, 1)));
      S.processScroll("\033[31m", "Health = " + PlayerInfo.getHealth(), true);
    }

    //This asks the player if they wish to run away or not
    String escape = "0";

    
    if(Encounter[event].getApproach_Options(Iinput, RanEvent, 2).equalsIgnoreCase("1")){
      S.processScroll("\033[0m", "You realise that you could escape, will you?", true);

      
      S.processScroll("\033[35m", "Press 1 to escape", true);
      S.processScroll("\033[35m", "Press 2 to fight", true);
      escape = keyboard.next();
    }

    return escape;
    
  }

  public void runBattle(){

    //This makes sure that the combatants are both still alive before beginning the round
    while((mob.getHealth() > 0) && (PlayerInfo.getHealth() > 0)){

      //This asks the player what they want to do, again there are two choices and a 'do nothing' alternative
      S.processScroll("\033[0m", "What will you do?", true);
      S.processScroll("\033[35m", "Press 1 to attack", true);
      S.processScroll("\033[35m", "Press 2 to dodge", true);
      String CombatChoice = keyboard.next();

      double PlayerMult = 0;
      double MobMult = 0;

      //This sets how hard the player and the mob will hit this round
      if(CombatChoice.equalsIgnoreCase("1")){
        
        PlayerMult = 1;
        MobMult = 1;

      }
      else if (CombatChoice.equalsIgnoreCase("2")){
        
        PlayerMult = 0.5;
        MobMult = 0.5;

      }
      else{

        PlayerMult = 0.5;
        MobMult = 2;

      }

      //This decides who goes first based on each combatant's speed
      boolean Second;

      int PlayerChance = D.rDice(PlayerInfo.getSpeed(), 1);
      int MobChance = D.rDice((int) mob.getSpeed(), 1);


      //This does the player's turn and then checks if the mob is still alive, if it is then the mob takes it's turn
      if(PlayerChance > MobChance){
        combat(PlayerMult, 0);

        if(mob.getHealth() > 0){
          combat(MobMult, 1);
        }

      }
      else
      //This does the mob's turn and then checks if the player is still alive, if they are then they take their turn{
        combat(MobMult, 1);

        if (PlayerInfo.getHealth() > 0){
          combat(PlayerMult, 0);
        }

      }

      


    }

    
  

  public void Aftermath(){

    //This tells the player that the mob is dead (maybe add different deaths for each mob)
    S.processScroll("\033[0m", "The " + mob.getName() + " thrashes around, finally, it lies still.", true);

    //This calculates the player's winnings
    int xp_gain = (int) mob.getXp() * Encounter[event].getMultiplier();
    int gold_gain = (int) mob.getGold() * Encounter[event].getMultiplier();

    //This tells the player what they have gained
    S.processScroll("\033[35m", "You gain " + xp_gain +" xp and " + gold_gain + " gold.", true);

    //This stores the player's new xp and gold values
    PlayerInfo.setXp(PlayerInfo.getXp() + xp_gain);
    PlayerInfo.setGold(PlayerInfo.getGold() + gold_gain);

    //This figures out what mob was killed
    int Ids[] = new int[4];

    String[] parts = mob.getId().split("<>");

    Ids[0] = Integer.parseInt(parts[0]); 
    Ids[1] = Integer.parseInt(parts[1]); 
    Ids[2] = Integer.parseInt(parts[2]); 
    Ids[3] = Integer.parseInt(parts[3]); 
    
    

    
    
    //This stores the death inside of the player's kill count
    PlayerInfo.setKills(PlayerInfo.getKills(Ids[0], Ids[1], Ids[2], Ids[3]) + Encounter[event].getMultiplier(), Ids[0], Ids[1], Ids[2], Ids[3]);

    S.processScroll("\033[39m\033[46m", "Number of " + mob.getName() + "s killed = " + PlayerInfo.getKills(Ids[0], Ids[1], Ids[2], Ids[3]), true);
    System.out.print("\033[49m");


  }

  public void mobfind(){

    int prefix = 0;
    int type = 0;
    int suffix = 0;

    String mobInput = "";


    //These decide if the player is strong enough to face a mob with each part, and randomly selects what that part will be 
    if(Encounter[event].getExtra(0) < PlayerInfo.getLevel()){
      prefix = D.rDice(Encounter[event].getExtra(0) + 2, 1);
      mobInput = mobInput + "p";
    }
    
    if(Encounter[event].getExtra(1) < PlayerInfo.getLevel()){
      type = D.rDice(Encounter[event].getExtra(1) + 2, 1);
      mobInput = mobInput + "t";
    }

    if(Encounter[event].getExtra(2) < PlayerInfo.getLevel()){
      suffix = D.rDice(Encounter[event].getExtra(2) + 2, 1);
      mobInput = mobInput + "s";
    }



    
    //This gets the mob out of MobMaker
    mob = M.processMobMaker(mobInput, -1, -1, Encounter[event].getMob_Id(), -1);

    
  }

  public void combat(double AttackMultiplier, int PlayerAttacker){

    int damage = 0;
    double damagedub = 0.0;

      if(PlayerAttacker == 0){
        damagedub = PlayerInfo.getWeapon().getWeapon_Attack() - (mob.getArmour() * Encounter[event].getMultiplier());
        
      }
      else{
        damagedub = (mob.getAttack() * Encounter[event].getMultiplier() * PlayerInfo.getDifficulty()) - PlayerInfo.getArmour();
      }

      
      damagedub = damagedub * AttackMultiplier;
      

      if (damagedub < 0){
        damage = 1;
      }
      else
      {
        damage = (int) damagedub;
      }    
      
      if (PlayerAttacker == 0){
        S.processScroll("\033[0m", "Your attack connects, the " + mob.getName() + " loses " + damage +" health", true);

        mob.setHealth(mob.getHealth() - damage);

        S.processScroll("\033[36m", "The " + mob.getName() + "\'s health = " + mob.getHealth(), true);
      } 
      else{
        S.processScroll("\033[0m", "The " + mob.getName() + " attacks you, you lose " + damage + " health", true);


        PlayerInfo.setHealth(PlayerInfo.getHealth() - damage);

        S.processScroll("\033[31m", "Health = " + PlayerInfo.getHealth(), true);
      } 

  }

}
