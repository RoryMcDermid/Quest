import java.util.*;


public class Menu
{

  Scanner keyboard = new Scanner(System.in);

  Scroll S = new Scroll();

  //It is inputted as a string to make it robust, but transferred as an int as ints are smaller than strings
  

  public int processMenu(){

    String SChoice = "";
    int IChoice = 0;

    //This allows the player to choose what to do
    S.processScroll("", "What do you wish to do?", true);
    S.processScroll("\033[35m", "Press 1 to play", true);
    S.processScroll("\033[35m", "Press 2 to view the leaderboard", true);
    S.processScroll("\033[35m", "Press 3 to see the Author's Notes", true);
    S.processScroll("\033[35m", "Press 4 to quit", true);
    SChoice =  keyboard.next();

    int whilefin = 0;

      while(whilefin == 0){
      if(SChoice.equalsIgnoreCase("1")){
        S.processScroll("\033[0m", "Enter Adventurer, and enjoy", true);
        System.out.println("");
        IChoice = 1;
        whilefin = 1;
      }
      else if(SChoice.equalsIgnoreCase("2")){
        //S.processScroll("\033[0m", "This hasn't yet been completed, come back at a later date", true);
        IChoice = 2;
        whilefin = 1;
      }
      else if(SChoice.equalsIgnoreCase("3")){
        IChoice = 3;
        whilefin = 1;
      }
      else if(SChoice.equalsIgnoreCase("4")){
        S.processScroll("\033[0m", "Farewell", true);
        IChoice = 4;
        whilefin = 1;
      }
      else{
        //This is warning the player about the vicious input validation
        S.processScroll("\033[0m", "Ok, this is the only place you will get this warning, enter the one of the correct inputs, or the consequences may be dire", true);
      }
    }


    return IChoice;
  }


}