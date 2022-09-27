//This is for events 1 and 2, but changing the code in the whole program at this stage would be time consuming and may cause large amounts of errors

public class Event1_Data
{
  
  private String Entrance;
  private String Approach[][];
  private String Approach_Options[][][];
  private int Mob_Id;
  private int Multiplier;
  private int Extra[];
  
  
  

  public Event1_Data() {

    //This is the opening statement for the encounter
    Entrance = "";
    
    //This is the three options that the person will get, first being the thing they do then the second and last ones being what happens
    Approach = new String[3][3];

    //This is the random outcome from the previous one,  the first dimension is for the approach that the player took, the second dimension is for the two random possibilities that could happen, the third is the data.
    //In the third dimension, the first variable the result, the second being the damage taken, and the third being if you run successfully (0 unsuccessful, 1 successful)
    Approach_Options = new String[3][2][3];

    //The mob id is for what mob base is used for the event
    Mob_Id = 0;
    
    //The multiplier is for if an event buffs a specific mob
    Multiplier = 0;

    //Extra is for prefix[0], type[1] and suffix[2], this is the specific chances of each part happening
    Extra = new int[3];

  }

  
  //This outputs the value of entrance
  public String getEntrance(){
    return Entrance;
  }

  //This inputs the value of entrance
  public void setEntrance(String in){
    Entrance = in;
  }

  //This outputs the value of approach, using d1 and d2 as the point in the array that it is
  public String getApproach(int D1, int D2){
    return Approach[D1][D2];
  }

  //This inputs the value of approach, using d1 and d2 as the point in the array that it is
  public void setApproach(String in, int D1, int D2){
    Approach[D1][D2] = in;
  }

  //This outputs the value of approach_options, using d1, d2 and d3 as the point in the array that it is
  public String getApproach_Options(int D1, int D2, int D3){
    return Approach_Options[D1][D2][D3];
  }

  //This inputs the value of approach_options, using d1, d2 and d3 as the point in the array that it is
  public void setApproach_Options(String in, int D1, int D2, int D3){
    Approach_Options[D1][D2][D3] = in;
  }

  //This outputs the value of mob_id, allowing the event to know which mob to use
  public int getMob_Id(){
    return Mob_Id;
  }

  //This inputs the value of mob_id
  public void setMob_Id(int in){
    Mob_Id = in;
  }

  //This outputs the value of multiplier, to buff the mob as required
  public int getMultiplier(){
    return Multiplier;
  }

  //This inputs the value of multiplier, for buffing the mob as required
  public void setMultiplier(int in){
    Multiplier = in;
  }

  //This outputs the value of extra, using d1  as the point in the array that it is, so it knows if it is prefix[0], type[1] or suffix[2]
  public int getExtra(int D1){
    return Extra[D1];
  }
  
  //This inputs the value of extra, using d1  as the point in the array that it is, so it knows if it is prefix[0], type[1] or suffix[2]
  public void setExtra(int in, int D1){
    Extra[D1] = in;
  }

}