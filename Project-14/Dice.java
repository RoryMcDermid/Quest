import java.util.Random;

public class Dice
{

    
  Random rand = new Random();

  
  
  
  

  public void processDice(){

    int sides = 0;
    int dice = 0;

    //Sides is the maximum number rollable in one roll
    //Dice is how many rolls happen
    rDice(sides, dice);




  }

    public int rDice (int sides, int dice){
    
    int score = 0;

      for (int i = 0; i < dice; i++){
        
        score =  score + (rand.nextInt(sides));

      }
    return score;
    }
  
}