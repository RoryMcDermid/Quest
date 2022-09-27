import java.io.*;


public class MobMaker 
{

  MobInitiate V = new MobInitiate(); 
  Dice D = new Dice();

  int prefix_num;
  int base_num;
  int type_num;
  int suffix_num;

  

  Mob_Data mob;
  Mob_Data base_list[];
  Mob_Data prefix_list[];
  Mob_Data suffix_list[];
  Mob_Data type_list[];

  //This is to make a randomised mob, Variables is to limit which of prefix, type and suffix are used
  public Mob_Data processMobMaker(String Variables, int PrefixIn, int TypeIn, int BaseIn, int SuffixIn)   {

    mob = new Mob_Data();

    //This is to initialise each of the mob parts (if this is reworked, make a way to only read in the specific part that has been chosen)
    base_list = V.processMobInitiate("Mob/Csvs/base.csv");
    prefix_list = V.processMobInitiate("Mob/Csvs/prefix.csv");
    suffix_list = V.processMobInitiate("Mob/Csvs/suffix.csv");
    type_list = V.processMobInitiate("Mob/Csvs/type.csv");
    

    int size = Variables.length();
    
    int validation[] = new int[3];


    //this is to check which of prefix, type and suffix are to be used
    for (int i = 0; i < size; i++) {
      
      String letter = Variables.substring(0, 1);
      if(letter.equalsIgnoreCase("t")){
        validation[0] = 1;
      }
      if(letter.equalsIgnoreCase("p")){
        validation[1] = 1;
      }
      if(letter.equalsIgnoreCase("s")){
        validation[2] = 1;
      }
    }
    
    //This is to decide what base prefix type and suffix will be
    RNG(PrefixIn, TypeIn, BaseIn, SuffixIn);
    
    Base();

    //This is to add the extras to base, or just add nothing if needed
    if (validation[0] == 1){
      Type(true);
    }
    else{
      Type(false);
    }

    if (validation[1] == 1){
      Prefix(true);
    }
    else{
      Prefix(false);
    }
    
    if (validation[2] == 1){
      Suffix(true);
    }
    else{
      Suffix(false);
    }
    

    return mob;
  }

  public void RNG(int PrefixIn, int TypeIn, int BaseIn, int SuffixIn){

    //if an input is -1, then it gets randomised, otherwise it is left, (i.e. most of the events have a specific mob that is in them, so base shouldn't be random, but this may not always be the case)

    if (BaseIn == -1){
      base_num = D.rDice(base_list.length, 1);
    }
    else{
      base_num = BaseIn;
    }


    if (PrefixIn == -1){
      prefix_num = D.rDice(prefix_list.length, 1);
    }
    else{
      prefix_num = PrefixIn;
    }

    if (TypeIn == -1){
      type_num = D.rDice(type_list.length, 1);
    }
    else{
      type_num = TypeIn;
    }

    if (SuffixIn == -1){
      suffix_num = D.rDice(suffix_list.length, 1);
    }
    else{
      suffix_num = SuffixIn;
    }
  }

  public void Base(){

    //This is to set base, no checks are needed to see if it is there, as base will always exist
    mob.setName(base_list[base_num].getName());
    mob.setAttack(base_list[base_num].getAttack());
    mob.setHealth(base_list[base_num].getHealth());
    mob.setArmour(base_list[base_num].getArmour());
    mob.setSpeed(base_list[base_num].getSpeed());
    mob.setGold(base_list[base_num].getGold());
    mob.setXp(base_list[base_num].getXp());
    mob.setId(base_list[base_num].getId());

  }

  public void Prefix(boolean blank){
    
    //This adds the prefix if it is ment to be added
    if(blank = true){
      if (prefix_num != 0){
        mob.setName(prefix_list[prefix_num].getName() + " " + mob.getName());
      }
      mob.setAttack(mob.getAttack() * prefix_list[prefix_num].getAttack());
      mob.setHealth(mob.getHealth() * prefix_list[prefix_num].getHealth());
      mob.setArmour(mob.getArmour() * prefix_list[prefix_num].getArmour());
      mob.setSpeed(mob.getSpeed() * prefix_list[prefix_num].getSpeed());
      mob.setGold(mob.getGold() * prefix_list[prefix_num].getGold());
      mob.setXp(mob.getXp() * prefix_list[prefix_num].getXp());
      mob.setId(prefix_list[prefix_num].getId() + "<>" + mob.getId());
    }
    else{
      //This adds 0 to the id to show that nothing has been added
      mob.setId("0" + "<>" + mob.getId());
    }
   
  }

  public void Type(boolean blank) {

    //This adds the type if it is ment to be added
    if(blank = true){
      if(type_num != 0){
        mob.setName(type_list[type_num].getName() + " " + mob.getName());
      }
      mob.setAttack(mob.getAttack() * type_list[type_num].getAttack());
      mob.setHealth(mob.getHealth() * type_list[type_num].getHealth());
      mob.setArmour(mob.getArmour() * type_list[type_num].getArmour());
      mob.setSpeed(mob.getSpeed() * type_list[type_num].getSpeed());
      mob.setGold(mob.getGold() * type_list[type_num].getGold());
      mob.setXp(mob.getXp() * type_list[type_num].getXp());
      mob.setId(type_list[type_num].getId() + "<>" + mob.getId());
    }
    //This adds 0 to the id to show that nothing has been added
    else{
      mob.setId("0" + "<>" + mob.getId());
    }
    
  }

  public void Suffix(boolean blank) {
    
    //This adds the suffix if it is ment to be added
    if(blank = true){
      if(suffix_num != 0){
        mob.setName(mob.getName() + " " + suffix_list[suffix_num].getName());
      }
      mob.setAttack(mob.getAttack() * suffix_list[suffix_num].getAttack());
      mob.setHealth(mob.getHealth() * suffix_list[suffix_num].getHealth());
      mob.setArmour(mob.getArmour() * suffix_list[suffix_num].getArmour());
      mob.setSpeed(mob.getSpeed() * suffix_list[suffix_num].getSpeed());
      mob.setGold(mob.getGold() * suffix_list[suffix_num].getGold());
      mob.setXp(mob.getXp() * suffix_list[suffix_num].getXp());
      mob.setId(mob.getId() + "<>" + suffix_list[suffix_num].getId());
    }
    //This adds 0 to the id to show that nothing has been added
    else{
      mob.setId(mob.getId() + "<>" + "0");
    }
    
  }

 

}

